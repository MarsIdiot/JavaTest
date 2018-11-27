package Proxy;

import org.junit.Test;


public class Bird2_Extends  extends Bird {
    @Override
    @Test
    public void fly() {
        long start = System.currentTimeMillis();

        super.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}
