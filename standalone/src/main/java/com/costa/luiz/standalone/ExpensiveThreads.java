package com.costa.luiz.standalone;

import java.time.Duration;

@SuppressWarnings("java:S106")
public class ExpensiveThreads {

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10_000;
        for (int idx = 0; idx < numberOfThreads; idx++) {
            new Thread(ExpensiveThreads::doSomeIOWork).start();
        }
        Thread.sleep(Duration.ofSeconds(5));
        System.out.println("Done");
    }

    public static void doSomeIOWork() {
        try {
            System.out.println(Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
