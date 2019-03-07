package Exception;

import org.junit.Test;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/10/23 11:33
 */

public class Try_catch {
    @Test
    public void try_catch(){
        try{
            System.out.println("try正常");
            int a=1/0;
            System.out.println("try异常后");

        }catch (RuntimeException re){
            System.out.println("异常原因1:"+re.getMessage());

        }catch (Exception e){
            System.out.println("异常原因2:"+e.getMessage());

        }
    }

    /**
     * 访问堆栈跟踪信息
     */

    @Test
    public void try_catch_stackTrace(){
        try{
            System.out.println("try正常");
            int a=1/0;
            System.out.println("try异常x后");
        }catch (Exception e){
            StackTraceElement[] elements = e.getStackTrace();

            System.out.println("异常原因2:"+e.getMessage());

        }
    }
}














