package tcp_udp_socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @Description:GreetingServer
 * @Auther:
 * @Date: 2018/10/23 14:38
 */
public class GreetingServer extends Thread{
    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                //创建好ServerSocket，等待连接
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");

                //若连接成功，则会返回一个Socket对象，与客户端Socket进行通信
                Socket server = serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());

                //获取远程客户端发的消息
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());

                //给远程客户端发消息
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");

                //关闭服务
                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main(String [] args)
    {
        //int port = Integer.parseInt(args[0]);
        try
        {
            Thread t = new GreetingServer(6066);
            t.run();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
