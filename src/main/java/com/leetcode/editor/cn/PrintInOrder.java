//Suppose we have a class: 
//
// 
//public class Foo {
//  public void first() { print("first"); }
//  public void second() { print("second"); }
//  public void third() { print("third"); }
//}
// 
//
// The same instance of Foo will be passed to three different threads. Thread A 
//will call first(), thread B will call second(), and thread C will call third(). 
//Design a mechanism and modify the program to ensure that second() is executed 
//after first(), and third() is executed after second(). 
//
// Note: 
//
// We do not know how the threads will be scheduled in the operating system, 
//even though the numbers in the input seem to imply the ordering. The input format 
//you see is mainly to ensure our tests' comprehensiveness. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3]
//Output: "firstsecondthird"
//Explanation: There are three threads being fired asynchronously. The input [1,
//2,3] means thread A calls first(), thread B calls second(), and thread C calls 
//third(). "firstsecondthird" is the correct output.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,3,2]
//Output: "firstsecondthird"
//Explanation: The input [1,3,2] means thread A calls first(), thread B calls 
//third(), and thread C calls second(). "firstsecondthird" is the correct output.
// 
//
// 
// Constraints: 
//
// 
// nums is a permutation of [1, 2, 3]. 
// 
//
// Related Topics 多线程 👍 548 👎 0


package com.leetcode.editor.cn;

import java.util.concurrent.Semaphore;

/**
 *
 * [1114]Print in Order
 *
 */
public class PrintInOrder {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new PrintInOrder().new Foo();

        new Thread(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Foo {

        private final Semaphore s1 = new Semaphore(1);
        private final Semaphore s2 = new Semaphore(0);
        private final Semaphore s3 = new Semaphore(0);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            s1.acquire();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            s2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            s2.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            s3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            s3.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}