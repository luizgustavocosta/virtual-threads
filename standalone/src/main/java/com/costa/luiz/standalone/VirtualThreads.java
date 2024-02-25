package com.costa.luiz.standalone;

import java.time.Duration;

@SuppressWarnings("java:S106")
public class VirtualThreads {

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 100_000;
        for (int idx = 0; idx < numberOfThreads; idx++) {
            Thread.startVirtualThread(VirtualThreads::doSomeIOWork);
        }
        Thread.sleep(Duration.ofSeconds(10));
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
