package com.mycompany.app;

public class BubbleSort {
    private String order = "ASC";

    public String getOrder() {
        return order;
    }

    public void setOrder(String expectOrder) throws Exception {
        expectOrder = expectOrder.toUpperCase();
        if (expectOrder == "ASC" || expectOrder == "DESC") {
            order = expectOrder;
        } else {
            throw new Exception("order 选项不符合 ASC 或者 DESC");
        }
    }

    /**
     * 比较函数
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public <T extends Comparable> boolean compare(T a, T b) {
        return order == "DESC"
                ? a.compareTo(b) < 0
                : a.compareTo(b) > 0;
    }

    /**
     * 冒泡排序算法实现
     *
     * @param arr
     * @param <T>
     * @return
     */
    public <T extends Comparable> T[] sort(T[] arr) {
        int len = arr.length;
        for (int i = len - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (compare(arr[j], arr[j + 1])) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
