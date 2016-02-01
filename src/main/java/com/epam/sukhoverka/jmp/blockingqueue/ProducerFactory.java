package com.epam.sukhoverka.jmp.blockingqueue;

import com.epam.sukhoverka.jmp.java8compatible.RandomUtils;
import org.apache.log4j.Logger;

import java.util.function.Supplier;

public class ProducerFactory {

    private static final Logger LOGGER = Logger.getLogger(ProducerFactory.class);

    public static Supplier<Integer> createProducer() {
        return () -> {
            Integer produceValue = RandomUtils.generatePositiveRandom(500);
            try {
                Thread.sleep(250);
                LOGGER.info(Thread.currentThread().getName() + " produced " + produceValue);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
            return produceValue;
        };
    }
}
