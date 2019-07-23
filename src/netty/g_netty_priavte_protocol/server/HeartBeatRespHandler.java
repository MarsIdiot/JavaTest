package netty.g_netty_priavte_protocol.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.g_netty_priavte_protocol.MessageType;
import netty.g_netty_priavte_protocol.struct.Header;
import netty.g_netty_priavte_protocol.struct.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Date: 2019/7/23 11:11
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    //private static final Logger LOG = LoggerFactory.getLogger(HeartBeatRespHandler.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 返回心跳应答消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            //LOG.info("Receive client heart beat message : ---> " + message);
            //LOG.info("服务端【2-心跳消息接收】:"+message);
            System.out.println("服务端【2-心跳消息接收】:" + message);
            NettyMessage heartBeat = buildHeatBeat();
            //LOG.info("Send heart beat response message to client : ---> " + heartBeat);
            //LOG.info("服务端【2-心跳消息响应】:"+heartBeat);
            System.out.println("服务端【2-心跳消息响应】:"+heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else
            ctx.fireChannelRead(msg);
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //LOG.info("客户端【心跳异常】"+cause.getMessage());
        System.out.println("服务端【心跳异常】"+cause.getMessage());

        cause.printStackTrace();

        ctx.fireExceptionCaught(cause);
    }
}
