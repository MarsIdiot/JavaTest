package Proxy;


public class BirdTimeProxy implements Flyable{

    private Flyable flyable;
    public BirdTimeProxy(){
    }
    public BirdTimeProxy(Flyable flyable){
        this.flyable=flyable;
    }


    public void fly() {
        long start = System.currentTimeMillis();

        flyable.fly();

        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}
