package com.epam.sukhoverka.jmp.java8compatible;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.epam.sukhoverka.jmp.java8compatible.RandomUtils.generatePositiveRandom;

public class ProducerConsumerApp {


    public static void main(String[] args) {
        SharedEntity sharedEntity = new SharedEntity(500);
        Supplier<SharedEntity> s1 = ProducerFactory.createProducer(sharedEntity, generatePositiveRandom(500));
        Supplier<SharedEntity> s2 = ProducerFactory.createProducer(sharedEntity, generatePositiveRandom(500));
        Supplier<SharedEntity> s3 = ProducerFactory.createProducer(sharedEntity, generatePositiveRandom(500));

        Consumer<SharedEntity> c1 = ConsumerFactory.createConsumer(generatePositiveRandom(500) + 1);
        Consumer<SharedEntity> c2 = ConsumerFactory.createConsumer(generatePositiveRandom(500) + 1);
        Consumer<SharedEntity> c3 = ConsumerFactory.createConsumer(generatePositiveRandom(500) + 1);


        s1.get();
        s2.get();
        s3.get();

        c1.accept(sharedEntity);
        c2.accept(sharedEntity);
        c3.accept(sharedEntity);


    }

}
