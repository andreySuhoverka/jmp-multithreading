package com.epam.sukhoverka.jmp;

import org.apache.log4j.Logger;

public class Consumer implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Consumer.class);
    private volatile SharedEntity entity;

    public Consumer(SharedEntity entity) {
        this.entity = entity;
    }

    public void run() {
        while (true) {
            synchronized (entity) {
                try {
                    while (entity.isEmpty()) {
                        entity.notify();
                        LOGGER.info("Consumer: force producer go from wait-set to blocked-set");
                        LOGGER.info("Consumer: consumer is going to wait set");
                        entity.wait();
                    }
                    LOGGER.info("Consumer: Consume entity");
                    entity.setEmpty(true);
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    LOGGER.error("Producer - thread was interrupted: " + e.getMessage());
                }
            }
        }

    }
}
