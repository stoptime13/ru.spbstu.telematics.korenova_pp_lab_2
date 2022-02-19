package ru.spbstu.telematics.java;


import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        Collision collision = new Collision();

        TrafficLight tLSN = new TrafficLight("SN", 0, collision);
        TrafficLight tLNS = new TrafficLight("NS", 0, collision);
        TrafficLight tLWE = new TrafficLight("WE", 0, collision);
        TrafficLight tLES = new TrafficLight("ES", 0, collision);

        int SN = 0;
        int NS = 0;
        int WE = 0;
        int ES = 0;

        ArrayList<Thread> threads = new ArrayList<Thread>();

        Thread tempThread;
        int typeNextCar;
        int numberCars = 10;

        for(int i = 0; i < numberCars; i++) {
            Thread.sleep(500);
            typeNextCar = random.nextInt(4);
            switch (typeNextCar) {
                case 0:
                    tempThread = new Thread(new Car(tLSN, SN++));
                    tempThread.start();
                    threads.add(tempThread);
                    break;
                case 1:
                    tempThread = new Thread(new Car(tLNS, NS++));
                    tempThread.start();
                    threads.add(tempThread);
                    break;
                case 2:
                    tempThread = new Thread(new Car(tLWE, WE++));
                    tempThread.start();
                    threads.add(tempThread);
                    break;
                case 3:
                    tempThread = new Thread(new Car(tLES, ES++));
                    tempThread.start();
                    threads.add(tempThread);
                    break;
            }
        }
        for (Thread th : threads) {
            th.join();
        }
    }
}
