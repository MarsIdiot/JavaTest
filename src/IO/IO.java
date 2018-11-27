package IO;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IO {
    @Test
    public void IOTest1() throws IOException {

        File file = new File( "IO.IO.demo"+File.separator+"demo1.txt"+File.separator+"demo1.txt");
       // file.mkdirs();
        if(file.exists()){
            System.out.println("已存在");
        }else{
            System.out.println("不存在，正在创建...");

            file.createNewFile();
            System.out.println("创建完成");
        }
        /*
        将文件修改的最后时间输出  须将毫秒数long转化为date
         */
        long lastModified = file.lastModified();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
        Date date = new Date(lastModified);
        String formatDate = dateFormat.format(date);
        /*String fileName = file.getName();
        String fileAbsolutePath = file.getAbsolutePath();
        long length = file.length();
        System.out.println(fileAbsolutePath);
        boolean exists = file.exists();
        URI uri = file.toURI();*/
       //file.delete();

        /*
        查看当前项目文件下的以"de"开头的文件
         */
       /* File dir = new File(".");
        File[] listFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File fileName) {
                String name = fileName.getName();
                return name.startsWith("de");
            }
        });
        for(File fileName:listFiles){
            System.out.println(fileName);// .\IO.IO.demo  .\demo1.txt

        }*/


       /*
       删除demo文件(包括其子孙文件),获取其文件下所有文件然后一一删除
        */
        /*File demoFile = new File("IO.IO.demo");
        String demoFileAbsolutePath = demoFile.getAbsolutePath();


        if(demoFile.isDirectory()){//1.判断是目录还是文件
            //2.获取demo文件夹下所有的文件对象
            File[] demoListFiles = demoFile.listFiles();
            for(File file2:demoListFiles){//逐个删除
                file2.deleteOnExit();
                System.out.println("删除子孙："+file2.getName());
            }
            demoFile.delete();
            System.out.println("删除自己："+demoFile.getName());

        }

        System.out.println(demoFileAbsolutePath);*/

        /*

         */
        File demoFile = new File("IO.IO.demo"+File.separator+"demo1.txt"+File.separator+"demo1.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(demoFile, "rw");

        //写
        String str="123我爱你啊，我的爱人！lalla1aaaa";
       // byte[] bytes = str.getBytes("utf-8");
        byte[] bytes = str.getBytes();

        randomAccessFile.write(bytes);

        //randomAccessFile.close();
        //读
        byte[] bytes2= new byte[1024];
         randomAccessFile.read(bytes2);

        String str2 = new String(bytes2,"GBK");
        System.out.println(str2);

    }


}
