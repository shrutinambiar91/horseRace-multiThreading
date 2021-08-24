package com.shrutinambiar.multithreading.multiThreadRace;

import com.shrutinambiar.multithreading.multiThreadRace.model.HorseStats;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class HorseRacing {

    public static void main(String[] args) {
        int totalParticipants = 5;
        ExecutorService horseThreads = Executors.newFixedThreadPool(totalParticipants);
        List<Future> futureList = new ArrayList<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalParticipants);

        try {
            for(int i = 0; i<5; i++) {
                futureList.add(horseThreads.submit(new Task(cyclicBarrier, totalParticipants)));
            }
            Thread.sleep(10000);

            String horseName = "";
            Long startTime = Long.MIN_VALUE;
            Long endTime = Long.MAX_VALUE;

            for(Future future : futureList) {
                HorseStats horseStats = (HorseStats) future.get(10, TimeUnit.SECONDS);
                if(horseStats.getEndTime() != null && endTime.compareTo(horseStats.getEndTime()) > 0) {
                    endTime = horseStats.getEndTime();
                    startTime = horseStats.getStartTime();
                    horseName = horseStats.getHorseName();

                }
            }
            System.out.println(String.format("%s finished the race in %o seconds and won the race", horseName, endTime-startTime));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            horseThreads.shutdown();
        }

    }

}
