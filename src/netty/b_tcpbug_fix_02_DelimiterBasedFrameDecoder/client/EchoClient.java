package netty.b_tcpbug_fix_02_DelimiterBasedFrameDecoder.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @Description:使用分割符解码器：DelimiterBasedFrameDecoder
 * @Auther:
 * @Date: 2019/6/5 14:41
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient() {
        this(0);
    }

    public EchoClient(int port) {
        this("localhost", port);
    }

    public EchoClient(String host, int port) {
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

                            //分割符解码器
                            ByteBuf delimiter =Unpooled.copiedBuffer("$_".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                            ch.pipeline().addLast(new StringEncoder());

                            ch.pipeline().addLast(new EchoClientHandler());
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
        new EchoClient("127.0.0.1", 65535).start(); // 连接127.0.0.1/65535，并启动
//        new EchoClient("127.0.0.1", 9251).start(); // 连接127.0.0.1/65535，并启动
    }
}