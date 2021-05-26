package plus.cove.jazzy.api.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConcurrentList {
    private static List<String> names = new ArrayList<>();

    // 添加n个元素到list中
    public void run(String name, int max) {
        for (int i = 0; i < max; i++) {
            try {
                Thread.sleep(new Random().nextInt(2));
            } catch (InterruptedException ie) {

            }
            names.add(name + i);
        }
    }

    public int size() {
        return names.size();
    }

    public void print() {
        System.out.println("集合大小：" + names.size());
        for (String name : names) {
            System.out.println("元素：" + name);
        }
    }
}
