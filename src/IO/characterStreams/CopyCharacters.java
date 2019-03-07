package IO.characterStreams;

import org.junit.Test;

import java.io.*;

/**
 * @Description:
 * @Date: 2019/3/7 11:37
 */
public class CopyCharacters {
    /**
     * 复制字符输入流到字符输出流
     * @throws IOException
     */
    @Test
    public void copyCharacters() throws IOException {

        try (FileReader in = new FileReader("src\\IO\\file\\test.txt");
             FileWriter out = new FileWriter("src\\IO\\file\\outTest.txt")) {
            int len = -1;
            char[] chars = new char[1024];
            int count = 1;
            //此处FileReade的read()方法只能读取到字符数组
            while ((len = in.read(chars)) != -1) {
                out.write(chars, 0, len - 1);
                System.out.println("第" + count++ + "输出：" + len + "字符");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
