package com.mycompany.app;

import java.util.List;

public class QuickSort {
    private <T extends Comparable<T>> int partition(List<T> arr, int first, int last) {
        T pivot = arr.get(first);
        while (first < last) {
            while (first < last && arr.get(last).compareTo(pivot) >= 0) --last;
            arr.set(first, arr.get(last));
            while (first < last && arr.get(first).compareTo(pivot) <= 0) ++first;
            arr.set(last, arr.get(first));
        }

        arr.set(first, pivot);

        return first;
    }

    private <T extends Comparable<T>> void innerSort(List<T> arr, int first, int last) {
        if (first < last) {
            int pivot = partition(arr, first, last);
            innerSort(arr, first, pivot - 1);
            innerSort(arr, pivot + 1, last);
        }
    }

    public <T extends Comparable<T>> void sort(List<T> arr) {
        innerSort(arr, 0, arr.size() - 1);
    }
}