package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

final public class Assigner {
    private List<List<Record>> partition(List<Record> records, int number) {
        List<List<Record>> container = new ArrayList(number);
        int total = records.size();
        int chunkSize = total / number;
        int lastSize = total - chunkSize * (number - 1);
        int nowSize = 0;

        for (int i = 0; i < number - 1; i++) {
            int nextSize = nowSize + chunkSize;
            container.add(records.subList(nowSize, nextSize));
            nowSize = nextSize;
        }

        container.add(records.subList(nowSize, nowSize + lastSize));

        return container;
    }

    private void mergeAndWrite(List<List<Record>> containers, Comparator<Record> comparator) throws
            FileNotFoundException, ExecutionException, InterruptedException {
        PrintWriter out = new PrintWriter(
                new FileOutputStream("data/sorted-big-file.txt", false));

        List<Record> buffer = new ArrayList<>(containers.get(0).size());
        for (List<Record> records : containers) {
            buffer.addAll(records);
        }
        Collections.sort(buffer);
        for (Record r : buffer) {
            out.println(r);
        }
//        int size = containers.size();
//
//        List<Integer> p = new ArrayList<>(Collections.nCopies(size, 0));
//        while (true) {
//            int number = p.size();
//
//            // 只有一个时，直接快速结束
//            if (1 == number) {
//                List<Record> records = containers.get(0);
//                for (int i = p.get(0); i < records.size(); ++i) {
//                    out.println(records.get(i));
//                }
//                break;
//            }
//
//            // 组装每个 chunk 的第一个元素
//            List<Record> buffer = new ArrayList<>(number); // 临时存放每一个 chunk 的值
//            for (int i = 0; i < number; i++) {
//                List<Record> records = containers.get(i);
//                buffer.add(records.get(p.get(i)));
//            }
//
//            // 排序，并且获取第一个放入缓存打印区域
//            List<Record> tempBuffer = new ArrayList<>(buffer);
//            Collections.sort(tempBuffer, comparator);
//            Record r = tempBuffer.get(0);
//            out.println(r); // 放入打印缓存区
//
//            // 调整工作指针
//            int removedIndex = buffer.indexOf(r);
//            List<Record> records = containers.get(removedIndex);
//            if (p.get(removedIndex) >= records.size() - 1) {
//                p.remove(removedIndex);
//                containers.remove(removedIndex);
//            } else {
//                p.set(removedIndex, p.get(removedIndex) + 1);
//            }
//        }
        out.flush();
        out.close();
        System.out.println("Merging and Writing DONE.");
    }

    public void assign(int chunkNumber, List<Record> records, Comparator<Record> comparator) throws
            ExecutionException, InterruptedException, FileNotFoundException {
        ExecutorService executor = Executors.newFixedThreadPool(chunkNumber);
        List<List<Record>> container = partition(records, 10);
        int size = container.size();

        for (int i = 0; i < size; i++) {
            Runnable assignee = new Sorter(i, container.get(i), comparator);
            executor.submit(assignee);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        mergeAndWrite(container, comparator);

        System.out.println("Finished all jobs");
    }
}
