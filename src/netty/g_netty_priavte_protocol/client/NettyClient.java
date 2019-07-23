package netty.g_netty_priavte_protocol.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import netty.g_netty_priavte_protocol.codec.NettyMessageDecoder;
import netty.g_netty_priavte_protocol.codec.NettyMessageEncoder;

import java.net.InetSocketAddress;

/**
 * @Description: 自定义基于netty的私有协议栈
 * @Auther:
 * @Date: 2019/6/5 14:41
 */
public class NettyClient {
    private final String host;
    private final int port;

    public NettyClient() {
        this(0);
    }

    public NettyClient(int port) {
        this("localhost", port);
    }

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();//线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group) // 注册线程池(即绑定线程组)
                    .channel(NioSocketChannel.class) // 使用NioSocketChannel来作为连接用的channel类
                    .remoteAddress(new InetSocketAddress(this.host, this.port)) // 绑定连接端口和host信息
                    .handler(new ChannelInitializer<SocketChannel>() { // 绑定连接初始化器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("connected...");

                            //编解码
                            //ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            //ch.pipeline().addLast(new NettyMessageEncoder());

                            //超时、重复登陆认证、心跳检测
                            ch.pipeline().addLast(new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            ch.pipeline().addLast(new HeartBeatReqHandler());

                            //业务
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("created..");

            ChannelFuture cf = b.connect().sync(); // 异步连接服务器
            System.out.println("connected..."); // 连接完成

            cf.channel().closeFuture().sync(); // 异步等待关闭连接channel
            System.out.println("closed.."); // 关闭完成
        } finally {
            group.shutdownGracefully().sync(); // 释放线程池资源
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyClient("127.0.0.1", 65535).start(); // 连接127.0.0.1/65535，并启动
//        new EchoClient("127.0.0.1", 9251).start(); // 连接127.0.0.1/65535，并启动
    }
}