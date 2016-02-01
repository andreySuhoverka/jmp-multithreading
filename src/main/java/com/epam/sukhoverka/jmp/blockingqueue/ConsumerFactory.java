package com.epam.sukhoverka.jmp.blockingqueue;

import org.apache.log4j.Logger;

import java.util.function.Consumer;

public class ConsumerFactory {

    private static final Logger LOGGER = Logger.getLogger(ConsumerFactory.class);

    public static Consumer<Integer> createConsumer() {
        return (Integer consumedValue) -> {
            try {
                Thread.sleep(1100);
                LOGGER.info(Thread.currentThread().getName() + " consumed " + consumedValue);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        };
    }
}
