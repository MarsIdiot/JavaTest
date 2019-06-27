package thread;

import org.junit.Test;

/**
 * @Description:暂停进程
 * @Date: 2019/3/11 15:05
 */
public class Sleep {

    @Test
    public void theradSleepTest() throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        for(int i=0;i<importantInfo.length;i++){
            Thread.sleep(5000);//执行到此，当前线程暂停5秒再继续执行
            System.out.println("第"+i+"个"+importantInfo[i]);
        }
    }
}
