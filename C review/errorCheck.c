
#include <errno.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>


void verificar_error(int fd, int n, int m) {
    if (fd < 0 || n < 0 || m < 0) {
        printf("Error: los valores no pueden ser negativos\n");
    } else {
        if (errno == 0) {
            printf("No error\n");
        } else {
            switch (errno) {
                case EAGAIN:
                    perror("Error EAGAIN: recurso temporalmente no disponible");
                    break;
                case EACCES:
                    perror("Error EACCES: permiso denegado");
                    break;
                case EBADF:
                    perror("Error EBADF: descriptor de archivo no válido");
                    break;
                default:
                    perror("Error desconocido");
                    break;
            }
        }
    }
}


float arr[] = {1.2, 2.2, 3.4, 4.5, 5.7, 3.2, 2.2, 4.2, 5.1, 10.5}; // arreglo de números flotantes

void escribir_arreglo(int fd) {
    int n = sizeof(arr) / sizeof(float); // determina la longitud del arreglo
    int escritos = write(fd, arr, n * sizeof(float)); // escribe el arreglo en el archivo
    if (escritos < 0) { // verifica si ocurrió un error al escribir el archivo
        verificar_error(fd, n, 0); // maneja el error
        close(fd); // cierra el archivo
        exit(1); // termina el programa
    }
}





