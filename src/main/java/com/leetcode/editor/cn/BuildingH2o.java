//There are two kinds of threads: oxygen and hydrogen. Your goal is to group 
//these threads to form water molecules. 
//
// There is a barrier where each thread has to wait until a complete molecule 
//can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and 
//releaseOxygen methods respectively, which will allow them to pass the barrier. These 
//threads should pass the barrier in groups of three, and they must immediately 
//bond with each other to form a water molecule. You must guarantee that all the 
//threads from one molecule bond before any other threads from the next molecule do. 
//
//
// In other words: 
//
// 
// If an oxygen thread arrives at the barrier when no hydrogen threads are 
//present, it must wait for two hydrogen threads. 
// If a hydrogen thread arrives at the barrier when no other threads are 
//present, it must wait for an oxygen thread and another hydrogen thread. 
// 
//
// We do not have to worry about matching the threads up explicitly; the 
//threads do not necessarily know which other threads they are paired up with. The key 
//is that threads pass the barriers in complete sets; thus, if we examine the 
//sequence of threads that bind and divide them into groups of three, each group should 
//contain one oxygen and two hydrogen threads. 
//
// Write synchronization code for oxygen and hydrogen molecules that enforces 
//these constraints. 
//
// 
// Example 1: 
//
// 
//Input: water = "HOH"
//Output: "HHO"
//Explanation: "HOH" and "OHH" are also valid answers.
// 
//
// Example 2: 
//
// 
//Input: water = "OOHHHH"
//Output: "HHOHHO"
//Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", 
//"HOHOHH" and "OHHOHH" are also valid answers.
// 
//
// 
// Constraints: 
//
// 
// 3 * n == water.length 
// 1 <= n <= 20 
// water[i] is either 'H' or 'O'. 
// There will be exactly 2 * n 'H' in water. 
// There will be exactly n 'O' in water. 
// 
//
// Related Topics 多线程 👍 164 👎 0


package com.leetcode.editor.cn;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 *
 * [1117]Building H2O
 *
 */
public class BuildingH2o {
    public static void main(String[] args) {
        H2O h2O = new BuildingH2o().new H2O();
        Thread tHydrogen1 = new Thread(() -> {
            try {
                h2O.hydrogen(() -> System.out.println("H"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread tHydrogen2 = new Thread(() -> {
            try {
                h2O.hydrogen(() -> System.out.println("H"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread tOxygen = new Thread(() -> {
            try {
                h2O.oxygen(() -> System.out.println("O"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        tHydrogen1.setName("Hydrogen1");
        tHydrogen2.setName("Hydrogen2");
        tOxygen.setName("Oxygen");
        tHydrogen1.start();
        tHydrogen2.start();
        tOxygen.start();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /*class H2O {
        Semaphore oSemaphore = new Semaphore(0);
        Semaphore hSemaphore = new Semaphore(2);
        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            hSemaphore.acquire();
            releaseHydrogen.run();
            oSemaphore.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            oSemaphore.acquire(2);
            releaseOxygen.run();
            hSemaphore.release(2);
        }
    }*/

    /*class H2O {
        private final ReentrantLock lock;
        private final Condition condition;
        private int hCount;
        public H2O() {
            this.hCount = 0;
            this.lock = new ReentrantLock();
            this.condition = this.lock.newCondition();
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            lock.lock();
            try{
                while (hCount == 2) {
                    condition.await();
                }
                hCount++;
                releaseHydrogen.run();
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            lock.lock();
            try{
                while (hCount != 2) {
                    condition.await();
                }
                releaseOxygen.run();
                hCount = 0;
                condition.signalAll();
            }finally {
                lock.unlock();
            }
        }
    }*/

    class H2O {
        private final Semaphore oSemaphore;
        private final  Semaphore hSemaphore;
        private final  CyclicBarrier barrier = new CyclicBarrier(3);

        public H2O() {
            hSemaphore = new Semaphore(2);
            oSemaphore = new Semaphore(1);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            hSemaphore.acquire(1);
            releaseHydrogen.run();
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
               Thread.currentThread().interrupt();
            }
            hSemaphore.release(1);
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            oSemaphore.acquire(1);
            releaseOxygen.run();
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            oSemaphore.release(1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}