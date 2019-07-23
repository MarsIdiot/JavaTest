package netty.g_netty_priavte_protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import netty.g_netty_priavte_protocol.codec.NettyMessageDecoder;
import netty.g_netty_priavte_protocol.codec.NettyMessageEncoder;

/**
 * 自定义基于netty的私有协议栈
 */
public class NettyServer {
    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(group) // 绑定线程池
                    .channel(NioServerSocketChannel.class) // 指定使用的channel  服务端
                    .localAddress(this.port)// 绑定监听端口
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("connected...; Client:" + ch.remoteAddress());


                            //编解码
                            //ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            //ch.pipeline().addLast(new NettyMessageEncoder());

                            //超时、重复登陆认证、心跳检测
                            //ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            //ch.pipeline().addLast(new LoginAuthRespHandler());
                            //ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());

                            //Netty序列化工具——编码解码
                            ch.pipeline().addLast(new ObjectDecoder(1024*1024,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            ch.pipeline().addLast(new ObjectEncoder());
                            //业务
                            ch.pipeline().addLast(new NettyServerHandler());


                        }
                    });
            ChannelFuture cf = sb.bind().sync(); // 服务器异步创建绑定
            System.out.println(NettyServer.class + " started and listen on " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // 关闭服务器通道
        } finally {
            group.shutdownGracefully().sync(); // 释放线程池资源
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer(65535).start(); // 启动
    }
}