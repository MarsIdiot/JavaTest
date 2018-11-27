package reflect;

/**
 * @Description:
 * @Auther: 周天浪（tianlang.zhou@ucarinc.com）
 * @Date: 2018/10/23 16:36
 */
public class Test {
    @org.junit.Test
    public void test1() throws Exception{
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
