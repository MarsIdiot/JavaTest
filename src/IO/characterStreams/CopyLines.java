package IO.characterStreams;

import org.junit.Test;

import java.io.*;

/**
 * @Description:以行为单位读取和输出   缓冲流
 * @Date: 2019/3/7 14:09
 */
public class CopyLines {
    @Test
    public void  copyLines() throws IOException {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader("src\\IO\\file\\test.txt"));
            out = new PrintWriter(new FileWriter("src\\IO\\file\\outTest-copyLines.txt"));
            String str;
            while ((str=in.readLine()) !=null ){
                out.println(str);
            }
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
