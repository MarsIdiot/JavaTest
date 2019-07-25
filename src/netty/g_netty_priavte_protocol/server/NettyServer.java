package netty.g_netty_priavte_protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import netty.g_netty_priavte_protocol.NettyConstant;
import netty.g_netty_priavte_protocol.codecMarshalling.NettyMessageDecoder;
import netty.g_netty_priavte_protocol.codecMarshalling.NettyMessageEncoder;

/**
 * 自定义基于netty的私有协议栈
 */
public class NettyServer {

    public void start(String host,int port) throws Exception {
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        EventLoopGroup workergroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossgroup,workergroup) // 绑定线程池
                    .channel(NioServerSocketChannel.class) // 指定使用的channel  服务端
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .localAddress(host,port)// 绑定服务端ip和端口
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("connected...; Client:" + ch.remoteAddress());


                            //编解码
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());

                            //超时、重复登陆认证、心跳检测
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthRespHandler());
                            ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());

                           /* //Netty序列化工具——编码解码
                            ch.pipeline().addLast(new ObjectDecoder(1024*1024,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            ch.pipeline().addLast(new ObjectEncoder());*/

                            //业务
                            ch.pipeline().addLast(new NettyServerHandler());


                        }
                    });
            ChannelFuture cf = sb.bind().sync(); // 服务器异步创建绑定
            System.out.println(NettyServer.class + " started and listen on " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // 关闭服务器通道
        } finally {
            bossgroup.shutdownGracefully().sync(); // 释放线程池资源
            workergroup.shutdownGracefully().sync(); // 释放线程池资源
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().start(NettyConstant.REMOTEIP,NettyConstant.REMOTEPORT); // 启动
    }
}