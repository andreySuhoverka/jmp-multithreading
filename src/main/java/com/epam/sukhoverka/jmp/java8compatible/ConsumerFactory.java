package com.epam.sukhoverka.jmp.java8compatible;

import org.apache.log4j.Logger;

import java.util.function.Consumer;

public class ConsumerFactory {

    private static final Logger LOGGER = Logger.getLogger(ConsumerFactory.class);

    public static Consumer<SharedEntity> createConsumer(int consumeValue) {
        final String CONSUMER_NAME = "consumer-" + consumeValue;
        return (se) -> {
            Thread t = new Thread(() -> {
                while(true) {
                    try {
                        synchronized (se) {
                            Thread.sleep(800);
                            boolean successfulGet = se.getValue(consumeValue);
                            if (!successfulGet) {
                                LOGGER.info(CONSUMER_NAME + " is going to wait-set because there is not enough value");
                                LOGGER.info(CONSUMER_NAME + " wakes up producer before going to wait-set");
                                se.notify();
                                se.wait();
                            }
                        }
                    } catch (InterruptedException e) {
                        LOGGER.error(e.getMessage());
                    }
                }

            });
            t.setName(CONSUMER_NAME);
            t.start();

        };
    }
}
