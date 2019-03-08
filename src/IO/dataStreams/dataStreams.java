package IO.dataStreams;

import IO.IO;
import org.junit.Test;

import java.io.*;

/**
 * @Description:数据流
 * @Date: 2019/3/8 13:51
 */
public class dataStreams {

    static final String dataFile = "src\\IO\\file\\invoicedata.txt";

    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = {
            "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain"
    };

    /**
     * 数据输出流测试
     * @throws IOException
     */
    @Test
    public void testDataOutputStrean() throws IOException {
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
        ){
            for(int i=0;i<prices.length;i++){
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
        }
    }

    /**
     * 数据输入流测试
     * @throws IOException
     */
    @Test
    public void testDataInputStrean() throws IOException {
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
        ){
            double price;
            int unti;
            String descs;
            while (true){
                System.out.format("%.2f "+" %d "+" %s%n",in.readDouble(),in.readInt(),in.readUTF());
            }
        }catch (EOFException e){

        }
    }
}
