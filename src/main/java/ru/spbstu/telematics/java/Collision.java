package ru.spbstu.telematics.java;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Collision {

        // пересекающиеся траектории движения
            int NS_WE;
            int ES_WE;
            int SN_WE;
            int ES_SN;

            Lock lockNS_WE = new ReentrantLock();
            Condition condNS_WE = lockNS_WE.newCondition();
            Lock lockES_WE = new ReentrantLock();
            Condition condES_WE = lockES_WE.newCondition();
            Lock lockSN_WE = new ReentrantLock();
            Condition condSN_WE = lockSN_WE.newCondition();
            Lock lockES_SN = new ReentrantLock();
            Condition condES_SN = lockES_SN.newCondition();

            public Collision() {
                NS_WE = 0;
                ES_WE = 0;
                SN_WE = 0;
                ES_SN = 0;
            }

            public void setPath(Lock lockP, Condition conditionP, int namePath){
                lockP.lock();
                int f = 0;
                if (namePath == 1) {
                    try {
                        conditionP.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        namePath = 1;
                        lockP.unlock();
                        f = 1;
                    }
                }
                if (f != 1) {
                    namePath = 1;
                    lockP.unlock();
                }
            }

            public void freePath(Lock lockP, Condition conditionP, int namePath) {
                lockP.lock();
                namePath = 0;
                conditionP.signal();
                lockP.unlock();
            }


}


