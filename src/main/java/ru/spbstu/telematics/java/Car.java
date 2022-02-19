package ru.spbstu.telematics.java;

public class Car implements Runnable {

    private TrafficLight trafficLight;
    private int id;

    Car(TrafficLight trafficLight, int id) {
        this.trafficLight = trafficLight;
        this.id = id;
    }

    public void run() {
        long sleepTime = 1000;
        System.out.println(trafficLight.getPath() + " (id:" + id + ") came to a crossroads");
        try {
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        trafficLight.lightUp(id);
        System.out.println(trafficLight.getPath() + " (id:" + id + ") departed from crossroads");

    }
}