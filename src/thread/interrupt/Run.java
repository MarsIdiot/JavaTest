package thread.interrupt;

/**
 * @Description:线程中断机制的演示  如何处理中断
 * @Date: 2019/3/11 16:18
 */
public class Run {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);//modify 2000 to 20
            thread.interrupt();//请求中断MyThread线程
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
