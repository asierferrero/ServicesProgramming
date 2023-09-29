#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    int fd;
    char message[100];

    // Open the FIFO for reading
    fd = open("myfifo", O_RDONLY);
    if (fd == -1) {
        perror("Error opening FIFO for reading");
        exit(EXIT_FAILURE);
    }

    // Read the message from the FIFO
    read(fd, message, sizeof(message));

    // Close the FIFO
    close(fd);

    printf("Received message: %s", message);

    return 0;
}
