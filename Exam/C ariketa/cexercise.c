#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <signal.h>

int main()
{
    // Signal handling
    signal(SIGINT, SIG_IGN);  // Ignore SIGINT in the father
    signal(SIGTERM, SIG_IGN); // Ignore SIGTERM in the father

    pid_t firstChildPid, secondChildPid;
    int pipeFd1[2], pipeFd2[2];

    // Create pipes
    if (pipe(pipeFd1) == -1 || pipe(pipeFd2) == -1)
    {
        perror("pipe");
        return 1;
    }

    // Create Son1
    firstChildPid = fork();

    if (firstChildPid == -1)
    {
        perror("fork");
        return 1;
    }

    if (firstChildPid == 0)
    {                      // Son1
        close(pipeFd1[0]); // Close the read end

        for (int i = 1; i <= 5000; i++)
        {
            if (i % 7 == 0)
            {
                write(pipeFd1[1], &i, sizeof(i));
            }
        }

        close(pipeFd1[1]);
        exit(0);
    }
    else
    { // Father
        // Create Son2
        secondChildPid = fork();

        if (secondChildPid == -1)
        {
            perror("fork");
            return 1;
        }

        if (secondChildPid == 0)
        {                      // Son2
            close(pipeFd1[1]); // Close the write end in Son2

            int number, sum = 0;
            while (read(pipeFd1[0], &number, sizeof(number)) > 0)
            {
                sum += number;
            }

            close(pipeFd1[0]);

            close(pipeFd2[0]); // Close the read end in Son2
            write(pipeFd2[1], &sum, sizeof(sum));
            close(pipeFd2[1]);

            exit(0);
        }
        else
        {                      // Father
            close(pipeFd1[0]); // Close the read end in the father
            close(pipeFd1[1]); // Close the write end in the father

            // Wait for both children to finish
            waitpid(firstChildPid, NULL, 0);
            waitpid(secondChildPid, NULL, 0);

            close(pipeFd2[1]); // Close the write end in the father

            int sumFromSon2;
            read(pipeFd2[0], &sumFromSon2, sizeof(sumFromSon2));
            close(pipeFd2[0]);

            printf("The sum received from Son2 is %d\n", sumFromSon2);
        }
    }

    return 0;
}
