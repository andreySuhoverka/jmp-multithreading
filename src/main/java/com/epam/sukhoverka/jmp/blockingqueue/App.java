package com.epam.sukhoverka.jmp.blockingqueue;

import org.apache.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);
    
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue(3);
        Consumer<Integer> consumer = ConsumerFactory.createConsumer();
        Supplier<Integer> supplier = ProducerFactory.createProducer();
        
        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    consumer.accept(queue.take());
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
        consumerThread.setName("consumer-thread");
        consumerThread.start();

        Thread producerThread = new Thread(() -> {
            while (true) {
                try {
                    queue.put(supplier.get());
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        });
        producerThread.setName("producer-thread");
        producerThread.start();
    }
}
