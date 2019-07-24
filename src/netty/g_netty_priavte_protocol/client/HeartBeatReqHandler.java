package netty.g_netty_priavte_protocol.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.ScheduledFuture;
import netty.g_netty_priavte_protocol.MessageType;
import netty.g_netty_priavte_protocol.struct.Header;
import netty.g_netty_priavte_protocol.struct.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date: 2019/7/23 10:41
 */
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

    //private static final Logger LOG = LoggerFactory.getLogger(HeartBeatReqHandler.class);
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 握手成功，主动发送心跳消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            //LOG.info("客户端【1-握手成功接收】:主动发送心跳消息");
            /**
             * 握手成功后，会开启一个定时器Schedule来定时发送心跳消息。
             * 心跳计时器的单位为毫秒，默认5000ms，即5秒发一条心跳消息
             */
            System.out.println("客户端【1-握手成功接收】:主动发送心跳消息");
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        } else if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            //LOG.info("Client receive server heart beat message : ---> " + message);
            //LOG.info("客户端【2-心跳消息接收】:"+message);
            System.out.println("客户端【2-心跳消息接收】:"+message);
        } else
            ctx.fireChannelRead(msg);
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heatBeat = buildHeatBeat();
            //LOG.info("Client send heart beat messsage to server : ---> " + heatBeat);
            //LOG.info("客户端【2-心跳消息请求】:"+heatBeat);
            System.out.println("客户端【2-心跳消息请求】:"+heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //LOG.info("客户端【心跳异常】"+cause.getMessage());
        System.out.println("客户端【心跳异常】"+cause.getMessage());

        cause.printStackTrace();
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

}
