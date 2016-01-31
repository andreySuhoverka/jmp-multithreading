package com.epam.sukhoverka.jmp;

import org.apache.log4j.Logger;

public class Producer implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Producer.class);
    private volatile SharedEntity entity;

    public Producer(SharedEntity entity) {
        this.entity = entity;
    }

    public void run() {
        while (true) {
            synchronized (entity) {
                try {
                    while (!entity.isEmpty()) {
                        entity.notify();
                        LOGGER.info("Producer: force consumer thread go from wait-set to blocked-set");
                        LOGGER.info("Producer: producer is going to wait-set");
                        entity.wait();
                    }
                    LOGGER.info("Producer: Put value into entity!");
                    entity.setEmpty(false);
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    LOGGER.error("Producer - thread was interrupted: " + e.getMessage());
                }
            }
        }
    }
}
