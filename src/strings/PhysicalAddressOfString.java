package strings;

import org.junit.Test;

/**
 * @Description:测试String/StringBuffer对象内容被修改后的指向的物理地址是否改变了
 * @Auther:
 * @Date: 2019/2/26 14:39
 */
public class PhysicalAddressOfString {

    /**
     * 结论：String对象改变值后，指向地址已变，String变量指向了一个新建的对象。
     */
    @Test
    public  void testString(){
        String name="周xx";
        System.out.println(name+":"+name.hashCode());//周xx:21500873

        change(name);
        System.out.println(name+":"+name.hashCode());

        name="李白一副老子天下第一的姿态";
        System.out.println(name+":"+name.hashCode());//李白一副老子天下第一的姿态:307586979

    }

    /**
     * 结论：StringBuffer改变值后,指向地址没变，还是同一个对象。
     */
    @Test
    public  void testStringBuffer(){
        StringBuffer name=new StringBuffer("周xx");
        System.out.println(name+":"+name.hashCode());//周xx:36202360

        name.append("李白一副老子天下第一的姿态");
        System.out.println(name+":"+name.hashCode());//周xx李白一副老子天下第一的姿态:36202360
    }


    /**
     * 形参不能改变String对象的值
     */
    @Test
    public void  testStringChangeByParam(){
        String name="周xx";
        System.out.println(name+":"+name.hashCode());//周xx:21500873

        change(name);
        System.out.println(name+":"+name.hashCode());//周xx:21500873
    }

    private   static  void change(String name){
        name="李白一副老子天下第一的姿态";
    }


    /**
     * 测试空串的长度
     */

    @Test
    public  void  stringLength(){
        String name="";
        System.out.println("空串长度:"+name.length());
        String password="  ";
        System.out.println("空字符串长度："+password.trim().length());

        System.out.println(name.equals(password.trim()));
    }








}
