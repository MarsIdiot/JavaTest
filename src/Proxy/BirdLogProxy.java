package Proxy;


import org.junit.Test;

public class BirdLogProxy implements Flyable{

    private Flyable flyable;
    public BirdLogProxy(){
    }
    public BirdLogProxy(Flyable flyable){
        this.flyable=flyable;
    }


    public void fly() {
        System.out.println("Bird fly start...");

        flyable.fly();

        System.out.println("Bird fly end...");
    }
}
