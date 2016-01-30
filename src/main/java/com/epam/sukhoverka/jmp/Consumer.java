package com.epam.sukhoverka.jmp;

public class Consumer implements Runnable {

    private SharedEntity entity;

    public Consumer(SharedEntity entity) {
        this.entity = entity;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (entity) {
                try {
                    while (entity.isEmpty()) {
                        entity.notify();
                        System.out.println("Consumer: force producer go from wait-set to blocked-set");
                        System.out.println("Consumer: consumer is going to wait set");
                        entity.wait();
                    }
                    System.out.println("Consumer: Consume entity");
                    entity.setEmpty(true);
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
