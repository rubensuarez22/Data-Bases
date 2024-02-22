#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>


#include "errorCheck.c"

int main() {
    int fd = creat("datos.txt", 0664); // crea el archivo
    if (fd < 0) { // verifica si ocurrió un error al crear el archivo
        verificar_error(fd, 0, 0); // maneja el error
        exit(1); // termina el programa
    }
    escribir_arreglo(fd); // escribe el contenido del arreglo en el archivo
    close(fd); // cierra el archivo
    printf("Arreglo escrito en el archivo datos.txt\n");
    return 0;
}
