package Proxy;

import java.util.Random;

public class Bird implements Flyable {

    @Override
    public void fly() {

        System.out.println("Bird is flying..");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
