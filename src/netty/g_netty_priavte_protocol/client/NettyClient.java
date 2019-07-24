package netty.g_netty_priavte_protocol.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import netty.g_netty_priavte_protocol.NettyConstant;
import netty.g_netty_priavte_protocol.codecMarshalling.NettyMessageDecoder;
import netty.g_netty_priavte_protocol.codecMarshalling.NettyMessageEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 自定义基于netty的私有协议栈
 * @Auther:
 * @Date: 2019/6/5 14:41
 */
public class NettyClient {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


    public void start(String host,int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();//线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group) // 注册线程池(即绑定线程组)
                    .channel(NioSocketChannel.class) // 使用NioSocketChannel来作为连接用的channel类
//                    .remoteAddress(new InetSocketAddress(host, port)) // 绑定连接端口和host信息
                    .handler(new ChannelInitializer<SocketChannel>() { // 绑定连接初始化器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("connected...");

                            //编解码
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());

                            //超时、重复登陆认证、心跳检测
                            ch.pipeline().addLast(new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            ch.pipeline().addLast(new HeartBeatReqHandler());

                            /*//Netty序列化工具——编码解码
                            ch.pipeline().addLast(new ObjectDecoder(1024*1024,ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                            ch.pipeline().addLast(new ObjectEncoder());*/

                            //业务
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("created..");
            ChannelFuture cf = b.connect(
                    new InetSocketAddress(host, port),
                    new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();// 异步连接服务器
            System.out.println("connected..."); // 连接完成

            cf.channel().closeFuture().sync(); // 异步等待关闭连接channel
            System.out.println("closed.."); // 关闭完成
        } finally {
            group.shutdownGracefully().sync(); // 释放线程池资源

            // 所有资源释放完成之后，清空资源，再次发起重连操作   (当服务器宕机等待重启期间  客户端会定时重连)
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            start(NettyConstant.REMOTEIP,NettyConstant.REMOTEPORT );// 发起重连操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyClient().start( NettyConstant.REMOTEIP,NettyConstant.REMOTEPORT);// 连接127.0.0.1/8080，并启动
    }
}