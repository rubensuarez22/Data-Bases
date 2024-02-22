#include <stdio.h>

void mover_ceros(int arr[], int n) {
    int i, j = n - 1;
    for(i = n - 1; i >= 0; i--) {
        if(arr[i] != 0) {
            arr[j--] = arr[i];
        }
    }
    while(j >= 0) {
        arr[j--] = 0;
    }
}

int main() {
    int arr[] = {1, 2, 0, 4, 9, 0, 3, 5, 1, 3};
    int n = sizeof(arr) / sizeof(arr[0]);

    mover_ceros(arr, n);

    for(int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}

