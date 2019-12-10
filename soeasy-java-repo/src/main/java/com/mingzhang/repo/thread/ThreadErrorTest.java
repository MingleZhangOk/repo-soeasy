package com.mingzhang.repo.thread;

public class ThreadErrorTest {
    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ThreadDemo.add();
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println("number===" + ThreadDemo.count);
    }

    static class ThreadDemo {
        static int count;

        static void add() {
            System.out.println(count++);
        }
    }
}
