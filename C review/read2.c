#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include "errorCheck.c"

int main() {
    int fd = open("datos2.txt", 0);
    verificar_error(fd, 0, 0);

    float num;
    int n;

    while ((n = read(fd, &num, sizeof(float))) > 0) {
        printf("%.2f\n", num);
    }

    close(fd);

    return 0;
}

