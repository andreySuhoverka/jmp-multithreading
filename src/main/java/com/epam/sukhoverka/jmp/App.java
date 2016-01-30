package com.epam.sukhoverka.jmp;

public class App {

    public static void main(String[] args) {
        SharedEntity entity = new SharedEntity();
        new Thread(new Producer(entity)).start();
        new Thread(new Consumer(entity)).start();
    }
}
