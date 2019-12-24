package com.tth.yelowbus_attendant.model;

public class StoppageModel  {
    int id;
    String name;
    boolean isNextStop;
    boolean isFirstStop;
    boolean isLastStop;
    String time;


    public StoppageModel(int id, String name, boolean isNextStop, boolean isFirstStop, boolean isLastStop , String time) {
        this.id = id;
        this.name = name;
        this.isNextStop = isNextStop;
        this.isFirstStop = isFirstStop;
        this.isLastStop = isLastStop;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNextStop() {
        return isNextStop;
    }

    public void setNextStop(boolean nextStop) {
        isNextStop = nextStop;
    }

    public boolean isFirstStop() {
        return isFirstStop;
    }

    public void setFirstStop(boolean firstStop) {
        isFirstStop = firstStop;
    }

    public boolean isLastStop() {
        return isLastStop;
    }

    public void setLastStop(boolean lastStop) {
        isLastStop = lastStop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
