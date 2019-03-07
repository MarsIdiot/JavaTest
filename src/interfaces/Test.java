package interfaces;

/**
 * @Description:
 * @Auther:
 * @Date: 2019/2/22 15:45
 */
public class Test {
    public void test01(){
        DoItNew doItNew=new DoItNewImpl();
        doItNew.didItWork(1,2,"3");
    }
}
