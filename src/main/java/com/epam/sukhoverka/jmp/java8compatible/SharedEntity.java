package com.epam.sukhoverka.jmp.java8compatible;

import org.apache.log4j.Logger;

public class SharedEntity {

    private static int MAX_VALUE_AMOUNT = 800;
    private Integer actualValueAmount;
    private static final Logger LOGGER = Logger.getLogger(SharedEntity.class);

    public SharedEntity(Integer actualValueAmount) {
        if(actualValueAmount > MAX_VALUE_AMOUNT) {
            throw new IllegalArgumentException("Initial value amount exceeds max allowed size: " + MAX_VALUE_AMOUNT);
        }
        this.actualValueAmount = actualValueAmount;
    }

    public boolean getValue(int requiredValueAmount) {
        if(actualValueAmount - requiredValueAmount < 0) {
            return false;
        }
        LOGGER.info("Thread " + Thread.currentThread().getName() +" consumed amount: " + requiredValueAmount + " of " + actualValueAmount);
        actualValueAmount -= requiredValueAmount;
        return true;
    }

    public boolean addValue(int additionalValueAmount) {
        if(actualValueAmount + additionalValueAmount > MAX_VALUE_AMOUNT){
            return false;
        }
        LOGGER.info("Thread " + Thread.currentThread().getName() +" add amount: " + additionalValueAmount + " to " + actualValueAmount);
        actualValueAmount += additionalValueAmount;
        return true;
    }



}
