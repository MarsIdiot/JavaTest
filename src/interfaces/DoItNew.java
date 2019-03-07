package interfaces;

/**
 * @Description:
 * @Date: 2019/2/22 15:40
 */
public interface DoItNew {
    void doSomething(int i, double x);
    int doSomethingElse(String s);

    //默认方法必须实现
    default boolean didItWork(int i, double x, String s) {
        return false;
    }

    //静态方法必须实现
    static  boolean didiItWorks(int i, double x, String s) {
        return false;
    }
}
