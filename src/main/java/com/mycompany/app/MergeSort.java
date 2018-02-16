package com.mycompany.app;

public class MergeSort {
    void merge(int arr[], int first, int mid, int last) {
        int[] temp = new int[arr.length];
        int i = first, j = mid + 1;
        int m = mid, n = last;
        int k = 0;
        while (i <= m && j <= n) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= m) {
            temp[k++] = arr[i++];
        }

        while (j <= n) {
            temp[k++] = arr[j++];
        }

        for (i = 0; i < k; i++) {
            arr[first + i] = temp[i];
        }
    }

    void sort(int[] arr, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            sort(arr, first, mid);
            sort(arr, mid + 1, last);
            merge(arr, first, mid, last);
        }
    }
}
