package Proxy;

import org.junit.Test;


public class Bird3_JuHe  implements Flyable{

    private Bird bird;
    public Bird3_JuHe(){
    }
    public Bird3_JuHe(Bird bird){
        this.bird=bird;
    }


    public void fly() {
        long start = System.currentTimeMillis();

        bird.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}
