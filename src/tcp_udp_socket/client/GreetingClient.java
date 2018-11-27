package tcp_udp_socket.client;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/10/23 14:35
 */
public class GreetingClient {
    public static void main(String [] args)
    {
        //String serverName = args[0];
        String serverName="localhost";
        //int port = Integer.parseInt(args[1]);
        int port=6066;
        try
        {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            //创建Socket时就会去自动连接服务器
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

            //向服务器发消息
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            //接收来自服务器的消息
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
