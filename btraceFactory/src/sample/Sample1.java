package sample;

import java.util.concurrent.TimeUnit;

/**
 * @author pf-miles
 * 
 */
public class Sample1 {
    public static void main(String... args) throws Throwable {
        int count = 0;
        // Sample1 s = new Sample1();
        while (true) {
            TimeUnit.SECONDS.sleep(3);
            hello(count, "hello" + count);
            count++;
        }
    }

    public static void hello(int count, String msg) {
        System.out.println(count + msg);
    }
}
