package com.epam.sukhoverka.jmp.blockingqueue;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);
    
    public static void main(String[] args) {
        BlockingQueue<Integer> box = new ArrayBlockingQueue(10);

        Consumer<BlockingQueue> consumer1 = q -> {
            try {
                System.out.println("Consumer get " + box.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        Supplier<BlockingQueue> producer = () -> {
            Integer toRet = new Random().nextInt(100);
            System.out.println("Producer put " + toRet);
            try {
                box.put(toRet);
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return box;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);

/*        IntStream.range(0, 100).forEach(i -> {
            executorService.execute(producer::get);
            executorService.execute(() -> consumer1.accept(box));
        });*/

        IntStream.range(0, 100).forEach(i -> {
                    CompletableFuture.supplyAsync(producer, executorService).thenAccept(consumer1);
                }
        );

        executorService.shutdown();
        
/*        Thread consumerThread = new Thread(() -> {
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
        producerThread.start();*/
    }
}
