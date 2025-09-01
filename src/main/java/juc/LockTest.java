package juc;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicReference;

public class LockTest {

  /*  public static void main(String[] args) {

        ObjectLock objectLock = new ObjectLock();
        //objectLock.hashCode();
        synchronized (objectLock) {
            //通过JOL工具获取this的对象布局
            String printable = ClassLayout.parseInstance(objectLock).toPrintable();
            //输出对象布局
            System.out.println("lock = " + printable);
        }
    }*/
  public static void main(String[] args) {
      AtomicReference<String> ref = new AtomicReference<>("hello");

      String str1 = new String("hello"); // 堆上新对象
      String str2 = "hello";             // 常量池

      System.out.println(str1 == str2);            // false（地址不同）
      System.out.println(str1.equals(str2));       // true（内容相同）

      boolean success1 = ref.compareAndSet(str1, "world");
      System.out.println(success1); // false！因为 str1 != ref.value（引用不同）

      boolean success2 = ref.compareAndSet("hello", "world");
      System.out.println(success2); // true！因为 "hello" == ref.value（常量池同一对象）

      System.out.println(ref.get()); // world
  }
}
