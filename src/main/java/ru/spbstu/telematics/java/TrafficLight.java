package ru.spbstu.telematics.java;

public class TrafficLight {

    private final String path; // маршрут, по которому можно двигаться
    private int nextCar; // сл машина
    private final Collision collision; // случаи пересечения траекторий

    public TrafficLight(String path, int nextCar, Collision collision) {
        this.path = path;
        this.nextCar = nextCar;
        this.collision = collision;
    }

    public synchronized void lightUp(int id) {
        while (id != nextCar) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (path == "SN") {
            runSN(id);
        }
        if (path == "NS") {
            runNS(id);
        }
        if (path == "WE") {
            runWE(id);
        }

        if (path == "ES") {
            runES(id);
        }
        nextCar++;
        notifyAll();

    }

    public String getPath() {
        return path;
    }

    public synchronized void runSN(int id) {
        collision.setPath(collision.lockSN_WE, collision.condSN_WE,collision.SN_WE);
        collision.freePath(collision.lockSN_WE, collision.condSN_WE,collision.SN_WE);
        collision.setPath(collision.lockES_SN, collision.condES_SN,collision.ES_SN);
        collision.freePath(collision.lockES_SN, collision.condES_SN,collision.ES_SN);
    }

    public synchronized void runNS(int id) {
        collision.setPath(collision.lockNS_WE, collision.condNS_WE,collision.NS_WE);
        collision.freePath(collision.lockNS_WE, collision.condNS_WE,collision.NS_WE);
    }

    public synchronized void runWE(int id) {
        collision.setPath(collision.lockNS_WE, collision.condNS_WE,collision.NS_WE);
        collision.freePath(collision.lockNS_WE, collision.condNS_WE,collision.NS_WE);
        collision.setPath(collision.lockES_WE, collision.condES_WE,collision.ES_WE);
        collision.freePath(collision.lockES_WE, collision.condES_WE,collision.ES_WE);
        collision.setPath(collision.lockSN_WE, collision.condSN_WE,collision.SN_WE);
        collision.freePath(collision.lockSN_WE, collision.condSN_WE,collision.SN_WE);
    }

    public synchronized void runES(int id) {
        collision.setPath(collision.lockES_SN, collision.condES_SN,collision.ES_SN);
        collision.freePath(collision.lockES_SN, collision.condES_SN,collision.ES_SN);
        collision.setPath(collision.lockES_WE, collision.condES_WE,collision.ES_WE);
        collision.freePath(collision.lockES_WE, collision.condES_WE,collision.ES_WE);
    }
}