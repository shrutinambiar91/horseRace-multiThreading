package com.shrutinambiar.multithreading.multiThreadRace.model;

public class HorseStats {

    private String horseName;
    private Long startTime;
    private Long endTime;

    public HorseStats(String threadName, Long startTime, Long endTime) {
        this.horseName = threadName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getHorseName() {
        return horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
