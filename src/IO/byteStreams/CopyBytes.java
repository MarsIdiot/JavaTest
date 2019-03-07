package IO.byteStreams;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:
 * @Date: 2019/3/7 10:27
 */
public class CopyBytes {

    /**
     * 复制字节输入流到字节输出流
     * @throws IOException
     */
    @Test
    public void copyBytes() throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("src\\IO\\file\\test.txt");
            out = new FileOutputStream("src\\IO\\file\\outTest.txt");
            int len=-1;
            byte[] bytes = new byte[1024];
            int  count=1;
            while((len=in.read(bytes))!=-1){//将字节数组作为中间对象，直到将输入流中的数据读完为止。
                out.write(bytes,0,len-1);
                System.out.println("第"+count++ +"输出："+ len+"字节");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out !=null){
                out.close();
            }
            if(in != null){
                in.close();
            }
        }

    }
}
