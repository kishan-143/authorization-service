package com.torino.authorizationservice;

import java.util.Arrays;

class SmsThread implements Runnable {
    private int tid;

    public SmsThread(int tid){
        this.tid = tid;
    }
    @Override
    //synchronized public void run() {
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 50; i++) {
                System.out.println("SMS Thread" + this.tid + ":" + i);
            }
        }
        for (int i = 50; i < 60; i++) {
            System.out.println("SMS Thread" + this.tid + ":" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class EmailThread extends Thread {
//    public void start(int tid) {
//        System.out.println("Email Thread started...");
//        this.run(tid);
//    }
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Email Thread"  + ": " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class WhatsAppThread extends Thread {
    public void start(int tid) {
        System.out.println("Whatsapp Thread started...");
        this.run(tid);
    }
    synchronized public void run(int tid) {
        System.out.println("WhatsApp Thread started...");
        for (int i = 0; i < 10; i++) {
            System.out.println("WhatsApp Thread" + tid + ":" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ThreadLearning {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("argument printed" + Arrays.toString(args));

        SmsThread s = new SmsThread(1);
        Thread smsThread = new Thread(s);
        smsThread.start();

        Thread smsThread2 = new Thread(s);
        smsThread2.start();

        smsThread.join();
        System.out.println("SMS thread1 completed...");

        EmailThread emailThread = new EmailThread();
        emailThread.start();

        emailThread.join();
        System.out.println("Email thread1 completed...");

        smsThread2.join();
        System.out.println("SMS thread2 completed...");

        final String resource1 = "ratan jaiswal";
        final String resource2 = "vimal jaiswal";
        // t1 tries to lock resource1 then resource2
        Thread t1 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1: locked resource 1");

                    try { Thread.sleep(100);} catch (Exception e) {}

                    synchronized (resource2) {
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 2: locked resource 1");

                    try { Thread.sleep(100);} catch (Exception e) {}

                    synchronized (resource2) {
                        System.out.println("Thread 2: locked resource 2");
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}
