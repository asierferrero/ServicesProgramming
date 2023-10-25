#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main() {
    int pipe_fd1[2], pipe_fd2[2];
    pid_t child1, child2;
    char message[100];
    
    // Create the first pipe
    if (pipe(pipe_fd1) == -1) {
        perror("Pipe creation failed");
    }

    // Create the second pipe
    if (pipe(pipe_fd2) == -1) {
        perror("Pipe creation failed");
    }

    // Create the first child process
    child1 = fork();

    if (child1 == -1) {
        perror("Fork failed");
    }

    if (child1 == 0) {  // First child process
        close(pipe_fd1[1]); // Close the write end of the first pipe
        read(pipe_fd1[0], message, sizeof(message)); // Read the message from the parent
        close(pipe_fd1[0]); // Close the read end of the first pipe

        // Modify the message
        strcat(message, " (Modified by First Child)");

        // Send the modified message to the second child
        close(pipe_fd2[0]); // Close the read end of the second pipe
        write(pipe_fd2[1], message, sizeof(message));
        close(pipe_fd2[1]); // Close the write end of the second pipe
        
    } else {  // Parent process
        close(pipe_fd1[0]); // Close the read end of the first pipe

        // Send a message to the first child
        strcpy(message, "Hello from Parent");
        write(pipe_fd1[1], message, sizeof(message));
        close(pipe_fd1[1]); // Close the write end of the first pipe

        // Create the second child process
        child2 = fork();

        if (child2 == -1) {
            perror("Fork failed");
        }

        if (child2 == 0) {  // Second child process
            close(pipe_fd2[1]); // Close the write end of the second pipe
            read(pipe_fd2[0], message, sizeof(message)); // Read the modified message from the first child
            close(pipe_fd2[0]); // Close the read end of the second pipe

            // Print the received message
            printf("Second child received: %s\n", message);

        } else {
            // Wait for both child processes to complete
            wait(NULL);
            wait(NULL);
        }
    }

    return 0;
}



