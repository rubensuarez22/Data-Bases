#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include "errorCheck.c"

int main() {
    int fd = open("datos2.txt", 1 | O_CREAT, 0644);
    verificar_error(fd, 1 | O_CREAT, 0644);
    
    float arr[5] = {1.23, 4.56, 7.89, 2.34, 5.67};
    int n = sizeof(arr) / sizeof(float);
    
    for (int i = 0; i < n; i++) {
        int escritos = write(fd, &arr[i], sizeof(float));
        verificar_error(escritos, sizeof(float), i);
        printf("El numero escrito es: %.2f\n", arr[i]);
    }
    
    close(fd);
    return 0;
}





