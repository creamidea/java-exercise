package com.mycompany.app;

import java.util.LinkedList;

public class MergeSort {
    <T extends Comparable<T>> void merge(LinkedList<T> arr, int first, int mid, int last) {
        LinkedList<T> temp = new LinkedList<>();
        int i = first, j = mid + 1;
        int m = mid, n = last;
        while (i <= m && j <= n) {
            if (arr.get(i).compareTo(arr.get(j)) >= 1) {
                temp.add(arr.get(i++));
            } else {
                temp.add(arr.get(j++));
            }
        }

        while (i <= m) {
            temp.add(arr.get(i++));
        }

        while (j <= n) {
            temp.add(arr.get(j++));
        }

        for (i = 0; i < temp.size(); i++) {
            arr.set(first + i, temp.get(i));
        }
    }

    <T extends Comparable<T>> void innerSort(LinkedList<T> arr, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;
            innerSort(arr, first, mid);
            innerSort(arr, mid + 1, last);
            merge(arr, first, mid, last);
        }
    }

    <T extends Comparable<T>> void sort(LinkedList<T> arr) {
        this.innerSort(arr, 0, arr.size() - 1);
    }
}
