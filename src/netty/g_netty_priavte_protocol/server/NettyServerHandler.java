package netty.g_netty_priavte_protocol.server;

/**
 * @Description:
 * @Auther:
 * @Date: 2019/6/5 14:40
 */

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.c_netty_serializable.client.SubscribeReq;
import netty.c_netty_serializable.server.SubscribeResp;
import netty.g_netty_priavte_protocol.MessageType;
import netty.g_netty_priavte_protocol.client.NettyClientHandler;
import netty.g_netty_priavte_protocol.struct.Header;
import netty.g_netty_priavte_protocol.struct.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);

    /**
     * 接收客户端消息入口
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NettyMessage req= (NettyMessage) msg;

        ctx.writeAndFlush(buildServiceResp(req));//写入消息并调用channelReadComplete()全部输出到客户端


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("server channelReadComplete..");
        // 第一种方法：写一个空的buf，并刷新写出区域。完成后关闭sock channel连接。
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        //ctx.flush(); // 第二种方法：在client端关闭channel连接，这样的话，会触发两次channelReadComplete方法。
        //ctx.flush().close().sync(); // 第三种：改成这种写法也可以，但是这中写法，没有第一种方法的好。
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server occur exception:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close(); // 关闭发生异常的连接
    }


    private NettyMessage buildServiceResp(NettyMessage req) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.SERVICE_RESP.value());
        message.setHeader(header);
        message.setBody("业务响应消息测试:答复请求__"+req.getBody().toString());
        return message;
    }
}
