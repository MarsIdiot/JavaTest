package reflect;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/10/23 16:36
 */
public class Test {
    @org.junit.Test
    public void test1() throws Exception{


        int i = 3;
        i++;
        System.out.println(i);    // "4"
        ++i;
        System.out.println(i);    // "5"
        System.out.println(++i);  // "6"
        System.out.println(i++);  // "6"
        System.out.println(i);    // "7"

        long creditCardNumber = 1234_5678_9012_3456L;
        int a=1_2_34_56_78;
        System.out.println(a);
        System.out.println(creditCardNumber);
        //System.out.println("abc");
        Class<DemoClass> aClass = (Class<DemoClass>) Class.forName("reflect.DemoClass");
        DemoClass newInstance = aClass.newInstance();
        newInstance.sayHello("反射成功");
        newInstance.setAge(10);
        newInstance.setName("皇后大道东");
        newInstance.toStringTest();
        System.out.println(newInstance);


    }

}
