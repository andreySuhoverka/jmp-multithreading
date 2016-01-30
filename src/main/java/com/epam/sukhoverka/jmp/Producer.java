package com.epam.sukhoverka.jmp;

public class Producer implements Runnable {

    SharedEntity entity;

    public Producer(SharedEntity entity) {
        this.entity = entity;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (entity) {
                try {
                    while (!entity.isEmpty()) {
                        entity.notify();
                        System.out.println("Producer: force consumer thread go from wait-set to blocked-set");
                        System.out.println("Producer: producer is going to wait-set");
                        entity.wait();
                    }
                    System.out.println("Producer: Put value into empty!");
                    entity.setEmpty(false);
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
