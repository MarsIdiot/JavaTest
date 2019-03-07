package test;


import Inherit.MountainBike;
import org.junit.Test;

public class test1 {
    private  boolean success;
    private  Boolean Success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    @Test
    public void returnTest() {
        for(int i=0;i<10;i++){

            if(i>2){
                continue;
            }
            System.out.println("第几次"+i);
        }
    }


    @Test
    public void constructorTest(){
        String contractId="123";
        Long cId = Long.parseLong(contractId == null ? null : contractId.toString());
    }

    @Test
    public  void  inheritTest(){
        MountainBike mountainBike = new MountainBike();
    }
}
