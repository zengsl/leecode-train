//You have a function printNumber that can be called with an integer parameter 
//and prints it to the console. 
//
// 
// For example, calling printNumber(7) prints 7 to the console. 
// 
//
// You are given an instance of the class ZeroEvenOdd that has three functions: 
//zero, even, and odd. The same instance of ZeroEvenOdd will be passed to three 
//different threads: 
//
// 
// Thread A: calls zero() that should only output 0's. 
// Thread B: calls even() that should only output even numbers. 
// Thread C: calls odd() that should only output odd numbers. 
// 
//
// Modify the given class to output the series "010203040506..." where the 
//length of the series must be 2n. 
//
// Implement the ZeroEvenOdd class: 
//
// 
// ZeroEvenOdd(int n) Initializes the object with the number n that represents 
//the numbers that should be printed. 
// void zero(printNumber) Calls printNumber to output one zero. 
// void even(printNumber) Calls printNumber to output one even number. 
// void odd(printNumber) Calls printNumber to output one odd number. 
// 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: "0102"
//Explanation: There are three threads being fired asynchronously.
//One of them calls zero(), the other calls even(), and the last one calls odd()
//.
//"0102" is the correct output.
// 
//
// Example 2: 
//
// 
//Input: n = 5
//Output: "0102030405"
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 1000 
// 
//
// Related Topics 多线程 👍 169 👎 0


package com.leetcode.editor.cn;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 *
 * [1116]Print Zero Even Odd
 *
 */
public class PrintZeroEvenOdd {
    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new PrintZeroEvenOdd().new ZeroEvenOdd(2);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class ZeroEvenOdd {
        private int n;

        private final Semaphore s1 = new Semaphore(1);
        private final Semaphore s2 = new Semaphore(0);
        private final Semaphore s3 = new Semaphore(0);

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                s1.acquire();
                printNumber.accept(0);
                if (i % 2 == 0) {
                    s2.release();
                } else {
                    s3.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            int times = (n % 2 == 0) ? n : n - 1;
            for (int i = 2; i <= times; i += 2) {
                s2.acquire();
                printNumber.accept(i);
                s1.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            int times = (n % 2 == 0) ? n - 1 : n;
            for (int i = 1; i <= times; i += 2) {
                s3.acquire();
                printNumber.accept(i);
                s1.release();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}