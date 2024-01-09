#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    int fd;
    char message[100];

    // Open the FIFO for writing
    fd = open("myfifo", O_WRONLY);
    if (fd == -1) {
        perror("Error opening FIFO for writing");
        exit(EXIT_FAILURE);
    }

    // Prompt the user to enter a message
    printf("Enter a message: ");
    fgets(message, sizeof(message), stdin);

    // Write the message to the FIFO
    write(fd, message, strlen(message) + 1);

    // Close the FIFO
    close(fd);

    printf("Message sent to FIFO.\n");

    return 0;
}
