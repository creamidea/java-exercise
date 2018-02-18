package com.mycompany.app;

import java.util.LinkedList;

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
    public <T extends Comparable<T>> boolean compare(T a, T b) {
        return order == "DESC"
                ? a.compareTo(b) < 0
                : a.compareTo(b) > 0;
    }

    /**
     * 冒泡排序算法实现
     *
     * @param <T>
     * @param arr
     * @return
     */
    public <T extends Comparable<T>> LinkedList<T> sort(LinkedList<T> arr) {
        int len = arr.size();
        for (int i = len - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (compare(arr.get(j), arr.get(j + 1))) {
                    T temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j+1, temp);
                }
            }
        }
        return arr;
    }
}
