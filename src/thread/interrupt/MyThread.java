package thread.interrupt;

public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("should be stopped and exit");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
    }


    /**
     * 当检测到线程中断指示时，其后的代码该不会执行才对。
     * 改进一： 将break改为return
     */
    public void run1() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("should be stopped and exit");
                return;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
    }

    /**
     * 改进二：将return改为抛出InterruptedException异常
     *
     * 但是由于此run()方法继承Runable,不能抛出InterruptedException异常，
     *         @Override
     *         public void run() throws InterruptedException{//这是错误的
     *           //do something...
     *         }
     * 所以，在当前情况下，只能使用try-catch处理，此时，将异常放在catch中处理，
     * 但是，为了记录异常信息，最好别用e.printStackTrace(),
     * 因此,一个更好的解决方案是：调用 interrupt() 以 “重新中断” 当前线程,
     * 将异常信息继续传递扩大。
     */
    public void run2(){
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("should be stopped and exit");
                try {
                    throw  new  InterruptedException();
                } catch (InterruptedException e) {
                    /**这样处理不好
                     * System.out.println("catch interrupted exception");
                     * e.printStackTrace();
                     */
                    Thread.currentThread().interrupt();//这样处理比较好
                }
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
    }


}