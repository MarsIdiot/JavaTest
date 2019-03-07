package insideClass;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/12/11 14:38
 */
public class Outer {

    Object method(){
        //局部变量
        final int locvar=1;
        //内部类
        class Inner{
            private   void  displayLocvar(){
                System.out.println("locvar:"+locvar);
            }
        }
        Object in =new Inner();
        return in;
    }

    public static void main(String[] args){
        Outer outer=new Outer();
        Object obj = outer.method();



    }


}
