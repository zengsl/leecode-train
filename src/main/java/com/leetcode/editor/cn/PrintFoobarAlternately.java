//Suppose you are given the following code: 
//
// 
//class FooBar {
//  public void foo() {
//    for (int i = 0; i < n; i++) {
//      print("foo");
//    }
//  }
//
//  public void bar() {
//    for (int i = 0; i < n; i++) {
//      print("bar");
//    }
//  }
//}
// 
//
// The same instance of FooBar will be passed to two different threads: 
//
// 
// thread A will call foo(), while 
// thread B will call bar(). 
// 
//
// Modify the given program to output "foobar" n times. 
//
// 
// Example 1: 
//
// 
//Input: n = 1
//Output: "foobar"
//Explanation: There are two threads being fired asynchronously. One of them 
//calls foo(), while the other calls bar().
//"foobar" is being output 1 time.
// 
//
// Example 2: 
//
// 
//Input: n = 2
//Output: "foobarfoobar"
//Explanation: "foobar" is being output 2 times.
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 1000 
// 
//
// Related Topics 多线程 👍 221 👎 0


package com.leetcode.editor.cn;

import java.util.concurrent.Semaphore;

/**
 *
 * [1115]Print FooBar Alternately
 *
 */
public class PrintFoobarAlternately {
    public static void main(String[] args) {

        FooBar fooBar = new PrintFoobarAlternately().new FooBar(2);
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class FooBar {
        private final int n;

        public FooBar(int n) {
            this.n = n;
        }

        private final Semaphore s1 = new Semaphore(1);
        private final Semaphore s2 = new Semaphore(0);
        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                s1.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                s2.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                s2.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                s1.release();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}