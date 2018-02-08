package com.mycompany.app;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        App app = new App();

        String target = "Hello;World";
        char sep = ';';
        ArrayList<String> result = app.split(target, sep);

        for (String x : result) {
            System.out.println(x);
        }
    }

    /**
     * 根据给定的字符分割字符串
     *
     * @param target 处理字符串
     * @param separator 分隔符
     * @return
     */
    public ArrayList<String> split(String target, char separator) {
        ArrayList<String> list = new ArrayList<String>();
        int p = 0;
        int i = 0;
        int max = target.length();

        for (; i < max; i++) {
            char x = target.charAt(i);
            if (separator == x) {
                list.add(target.substring(p, i));
                p = i + 1;
            }
        }

        // 处理最后一截
        list.add(target.substring(p, i));

        return list;
    }
}
