package netty.g_netty_priavte_protocol.client;

/**
 * @Description:
 * @Date: 2019/7/23 10:40
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.g_netty_priavte_protocol.MessageType;
import netty.g_netty_priavte_protocol.struct.Header;
import netty.g_netty_priavte_protocol.struct.NettyMessage;
import org.apache.log4j.helpers.LogLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    //private static final Logger LOG = LoggerFactory.getLogger(LoginAuthReqHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //LogLog.debug("客户端【1-登陆请求】");
        System.out.println("客户端【1-登陆请求】");
        ctx.writeAndFlush(buildLoginReq());
//        ctx.writeAndFlush(100010);
        //LOG.info("客户端【1-登陆请求-参数】："+buildLoginReq());/
        System.out.println("客户端【1-登陆请求-参数】："+buildLoginReq());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        // 如果是握手应答消息，需要判断是否认证成功
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                // 握手失败，关闭连接
                //LOG.info("客户端【握手失败】");
                System.out.println("客户端【握手失败】");
                ctx.close();
            } else {
                //LOG.info("Login is ok : " + message);
                //LOG.info("客户端【握手成功】"+ message);
                System.out.println("客户端【握手成功】"+ message);
                ctx.fireChannelRead(msg);
            }
        } else
            ctx.fireChannelRead(msg);
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //LOG.info("客户端【重复性登陆认证异常】"+cause.getMessage());
        System.out.println("客户端【重复性登陆认证异常】"+cause.getMessage());
        ctx.fireExceptionCaught(cause);
    }
}
