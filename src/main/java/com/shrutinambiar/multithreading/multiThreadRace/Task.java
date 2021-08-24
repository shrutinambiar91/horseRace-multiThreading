package com.shrutinambiar.multithreading.multiThreadRace;

import com.shrutinambiar.multithreading.multiThreadRace.model.HorseStats;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class Task implements Callable<HorseStats> {

    private CyclicBarrier barrier;
    private int totalParticipants;

    public Task(CyclicBarrier barrier, int totalParticipants) {
        this.barrier = barrier;
        this.totalParticipants = totalParticipants;
    }

    @Override
    public HorseStats call() throws Exception {
        Long endTime = null;
        Long startTime = null;
        try {
            System.out.println("waiting for " + (totalParticipants - barrier.getNumberWaiting()) + " more horses to start the race");
            barrier.await();
            startTime = System.currentTimeMillis();
            System.out.println("Horse -> " + Thread.currentThread().getName() + " start time: " + startTime);
            Thread.sleep(new Random().nextInt(10));
            endTime = System.currentTimeMillis();
            System.out.println("Horse -> " + Thread.currentThread().getName() + " end time: " + endTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HorseStats(Thread.currentThread().getName(), startTime, endTime);
    }

}
