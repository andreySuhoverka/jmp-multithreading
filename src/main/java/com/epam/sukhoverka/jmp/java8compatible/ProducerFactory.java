package com.epam.sukhoverka.jmp.java8compatible;

import org.apache.log4j.Logger;

import java.util.function.Supplier;

public class ProducerFactory {

    private static final Logger LOGGER = Logger.getLogger(ProducerFactory.class);

    public static Supplier<SharedEntity> createProducer(SharedEntity sharedEntity, int produceValue) {
        final String PRODUCER_NAME = "producer-" + produceValue;
        return () -> {
            Thread t = new Thread(() -> {
                while (true) {
                    synchronized (sharedEntity) {
                        try {
                            Thread.sleep(700);
                            boolean successfulAddition = sharedEntity.addValue(produceValue);
                            if (!successfulAddition) {
                                LOGGER.info(PRODUCER_NAME + " is going to wait-set because the is enough value in shared entity");
                                LOGGER.info(PRODUCER_NAME + " wakes up consumer before going to wait-set");
                                sharedEntity.notify();
                                sharedEntity.wait();

                            }
                        } catch (InterruptedException e) {
                            LOGGER.error(e.getMessage());
                        }
                    }
                }

            });
            t.setName(PRODUCER_NAME);
            t.start();
            return sharedEntity;
        };
    }
}
