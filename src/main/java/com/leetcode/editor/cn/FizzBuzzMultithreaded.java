//You have the four functions: 
//
// 
// printFizz that prints the word "fizz" to the console, 
// printBuzz that prints the word "buzz" to the console, 
// printFizzBuzz that prints the word "fizzbuzz" to the console, and 
// printNumber that prints a given integer to the console. 
// 
//
// You are given an instance of the class FizzBuzz that has four functions: 
//fizz, buzz, fizzbuzz and number. The same instance of FizzBuzz will be passed to 
//four different threads: 
//
// 
// Thread A: calls fizz() that should output the word "fizz". 
// Thread B: calls buzz() that should output the word "buzz". 
// Thread C: calls fizzbuzz() that should output the word "fizzbuzz". 
// Thread D: calls number() that should only output the integers. 
// 
//
// Modify the given class to output the series [1, 2, "fizz", 4, "buzz", ...] 
//where the iᵗʰ token (1-indexed) of the series is: 
//
// 
// "fizzbuzz" if i is divisible by 3 and 5, 
// "fizz" if i is divisible by 3 and not 5, 
// "buzz" if i is divisible by 5 and not 3, or 
// i if i is not divisible by 3 or 5. 
// 
//
// Implement the FizzBuzz class: 
//
// 
// FizzBuzz(int n) Initializes the object with the number n that represents the 
//length of the sequence that should be printed. 
// void fizz(printFizz) Calls printFizz to output "fizz". 
// void buzz(printBuzz) Calls printBuzz to output "buzz". 
// void fizzbuzz(printFizzBuzz) Calls printFizzBuzz to output "fizzbuzz". 
// void number(printNumber) Calls printnumber to output the numbers. 
// 
//
// 
// Example 1: 
// Input: n = 15
//Output: [1,2,"fizz",4,"buzz","fizz",7,8,"fizz","buzz",11,"fizz",13,14,
//"fizzbuzz"]
// 
// Example 2: 
// Input: n = 5
//Output: [1,2,"fizz",4,"buzz"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 50 
// 
//
// Related Topics 多线程 👍 118 👎 0


package com.leetcode.editor.cn;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 *
 * [1195]Fizz Buzz Multithreaded
 *
 */
public class FizzBuzzMultithreaded {
    public static void main(String[] args) {
        FizzBuzz solution = new FizzBuzzMultithreaded().new FizzBuzz(4);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class FizzBuzz {
        private final int n;
        private final Semaphore semaphoreF;
        private final Semaphore semaphoreB;
        private final Semaphore semaphoreFb;
        private final Semaphore semaphoreD;

        public FizzBuzz(int n) {
            this.n = n;
            this.semaphoreF = new Semaphore(1);
            this.semaphoreB = new Semaphore(0);
            this.semaphoreFb = new Semaphore(0);
            this.semaphoreD = new Semaphore(0);
        }

        enum State {
            F,
            B,
            FB,
            D
        }

        private State computeState(int num) {
            if (num % 15 == 0) {
                return State.FB;
            } else if (num % 5 == 0) {
                return State.B;
            } else if (num % 3 == 0) {
                return State.F;
            } else {
                return State.D;
            }
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                semaphoreF.acquire();
                if (computeState(i) == State.F) {
                    printFizz.run();
                }
                semaphoreB.release();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                semaphoreB.acquire();
                if (computeState(i) == State.B) {
                    printBuzz.run();
                }
                semaphoreFb.release();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                semaphoreFb.acquire();
                if (computeState(i) == State.FB) {
                    printFizzBuzz.run();
                }
                semaphoreD.release();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                semaphoreD.acquire();
                if (computeState(i) == State.D) {
                    printNumber.accept(i);
                }
                semaphoreF.release();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}