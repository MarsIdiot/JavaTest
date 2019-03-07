package valuePassedAndreferencePassed;

import org.junit.Test;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/12/11 16:50
 */
public class ParamTest {


    /**
     * Java中到底是值传递还是引用传递
     *
     * result:
     *      print in pass1 , j is 20
     *      print in test1 , i is 10
     * 分析：实参未被改变
     * 结论：难道java是值传递？
     */
    public void pass1(int j) {
        j = 20;
        System.out.println("print in pass1 , j is " + j);
    }
    @Test
    public  void test1() {
        ParamTest pt = new ParamTest();

        int i = 10;
        pt.pass1(i );
        System.out.println("print in test1 , i is " + i);
    }


    /**
     * 假如传递的为包装类
     *
     * result:
     *      print in pass2 , j is 20
     *      print in test2 , i is 10
     * 分析：实参未被改变
     * 结论：难道java是值传递？
     */
    public void pass2(Integer j) {
        j = 20;
        System.out.println("print in pass2 , j is " + j);
    }
    @Test
    public  void test2() {
        ParamTest pt = new ParamTest();

        Integer i = 10;
        pt.pass2(i );
        System.out.println("print in test2 , i is " + i);
    }

    /**
     * 再来----
     * 假如传递的为对象
     *
     * result:
     *      print in pass3 , user is User{name='张三', password='123'}
     *      print in test3 , pass3user2 is User{name='张三', password='123'}
     * 分析：经过pass方法执行后，实参的值竟然被改变了，那按照上面的引用传递的定义，
     *      实际参数的值被改变了，这不就是引用传递了么?
     * 结论：难道——Java的方法中，在传递普通类型和封装类型的时候是值传递，在传递对象类型的时候是引用传递？？
     */

    public void pass3( User user) {
        user.setName("张三");
        user.setPassword("123");
        System.out.println("print in pass3 , user is " + user.toString());
    }
    @Test
    public  void test3() {
        ParamTest pt = new ParamTest();

        User user2 = new User("李四", "456");
        pt.pass3(user2 );
        System.out.println("print in test3 , pass3user2 is " + user2);
    }


    /**
     * 再来----
     *  我们在pass方法中，改变了user对象
     *
     * result:
     *      print in pass3 , user is User{name='张三', password='123'}
     *      print in test4 , user2 is User{name='李四', password='456'}
     * 分析：当改变user对象时，实参user没有被改变
     * 结论：
     */

    public void pass4( User user2) {
        User user=new User();//改变了user对象
        user=user2;
        user.setName("张三");
        user.setPassword("123");
        System.out.println("print in pass4 , user is " + user.toString());
    }
    @Test
    public  void test4() {
        ParamTest pt = new ParamTest();

        User user2 = new User("李四", "456");
        pt.pass4(user2 );
        System.out.println("print in test4 , user2 is " + user2);
    }


}
