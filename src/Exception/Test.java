package Exception;

/**
 * @Description:
 * @Date: 2019/3/1 17:04
 */
public class Test {

    /**
     * testMyBusinessException 和 testMyBusinessException02  测试自定义异常类
     */
    public  void testMyBusinessException(){
        try{
            int i=1/0;
        }catch (Exception e){
            throw  new MyBusinessException("0","自定义异常");
        }
    }

    @org.junit.Test
    public  void testMyBusinessException02(){
        try {
            this.testMyBusinessException();
        }catch (MyBusinessException e){
            System.out.println("erroCode:"+e.getErrCode());
            System.out.println("erroMessage:"+e.getMessage());
        }

    }

}
