package netty.g_netty_priavte_protocol.client;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.c_netty_serializable.client.SubscribeReq;
import netty.c_netty_serializable.server.SubscribeResp;
import netty.g_netty_priavte_protocol.MessageType;
import netty.g_netty_priavte_protocol.struct.Header;
import netty.g_netty_priavte_protocol.struct.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Auther:
 * @Date: 2019/6/5 14:41
 */

public  class NettyClientHandler extends SimpleChannelInboundHandler {


    private static final Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);


    /**
     * 向服务器发起消息入口
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOG.info("客户端【3-业务请求】：");
        for(int i=0;i<10;i++){
            ctx.writeAndFlush(buildServiceReq());
            LOG.info("客户端【3-业务请求-参数】："+buildServiceReq().toString());
        }

        // 必须存在flush
        // ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        // ctx.flush();
    }

    /**
     * 接收服务器响应的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage resp= (NettyMessage) msg;
        LOG.info("客户端【3-业务响应-参数】："+resp.toString());

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage resp= (NettyMessage) msg;
        LOG.info("客户端【3-业务响应-参数】："+resp.toString());
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client end;");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private NettyMessage buildServiceReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.SERVICE_REQ.value());
        message.setHeader(header);
        message.setBody("业务请求消息测试");
        return message;
    }
}