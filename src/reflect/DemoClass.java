package reflect;

import java.io.Serializable;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/10/23 16:36
 */
public class DemoClass  implements Serializable{
    private static final long serialVersionUID = 5178075526632027677L;
    private static String name;
    private static int age;

    public DemoClass() {

    }
    public DemoClass(String name,int age){
        this.name=name;
        this.age=age;
    }
    public void sayHello(String param) {
        System.out.println("hello"+param);
    }
    public  void toStringTest(){
        System.out.println(name+age);
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        DemoClass.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        DemoClass.age = age;
    }

}
