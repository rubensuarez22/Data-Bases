#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include "errorCheck.c"

int main() {
    int fd = open("datos.txt", 0); // abrir archivo en modo lectura
 
    float arr[10];
    int bytes_leidos = read(fd, arr, 10 * sizeof(float)); // leer el arreglo del archivo
    
    close(fd); // cerrar el archivo
    
    for (int i = 0; i < 10; i++) {
        printf("%.2f\n", arr[i]); // imprimir cada elemento del arreglo
    }
    
    verificar_error(fd, bytes_leidos, sizeof(arr)); // verificar si ha ocurrido algún error en open() o read()
    
    return 0;
}



