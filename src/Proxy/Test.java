package Proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    //测试父类Bird
    @org.junit.Test
    public  void test1(){
        Bird bird=new Bird();
        bird.fly();
    }

    //测试子类Bird2
    @org.junit.Test
    public  void test2(){
        Bird bird=new Bird2_Extends();
        bird.fly();
    }

    //测试子类BirdTimeProxy
    @org.junit.Test
    public  void test3(){
        Bird bird=new Bird();
        BirdTimeProxy time=new BirdTimeProxy(bird);
        time.fly();
    }

    //测试子类BirdLogProxy
    @org.junit.Test
    public  void test4(){
        Bird bird=new Bird();
        BirdLogProxy log=new BirdLogProxy(bird);
        log.fly();
    }

    //测试:先打印日志，再打印时间
    @org.junit.Test
    public  void test5(){
        Bird bird=new Bird();
        BirdLogProxy log= new BirdLogProxy(bird);
        BirdTimeProxy time=new BirdTimeProxy(log);
        time.fly();
    }

    /**
     *
     * 功能描述: 动态代理测试
     *
     * @param:
     * @return:
     * @auther:
     * @date: 2018/10/25 14:19
     */
    @org.junit.Test
    public  void test6(){
        Flyable objProxy = (Flyable) Proxy.newProxyInstance(
                Flyable.class.getClassLoader(), //与目标对象相同的类加载器
                new Class[]{Flyable.class}, //这个也行target.getClass().getInterfaces()
                new InvocationHandler() {
                    //invoke 代表的是执行代理对象的方法
                    @Override
                    //method：代表目标对象的方法字节码对象
                    //args:代表目标对象的响应的方法的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("目标方法前的逻辑");
                        //执行目标对象的方法
                        Object invoke = method.invoke(new Bird(), args);
                        System.out.println("目标方法后的逻辑");
                        return invoke;
                    }
                }
        );

        objProxy.fly();

    }

}
