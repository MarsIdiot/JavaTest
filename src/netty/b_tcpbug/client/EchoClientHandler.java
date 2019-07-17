package netty.b_tcpbug.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description:
 * @Auther:
 * @Date: 2019/6/5 14:41
 */

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int counter;
    private byte[] req;

    public  EchoClientHandler(){
        req=("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
    }
    /**
     * 向服务器发起消息入口
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channelActive..");
        //ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8)); // 必须有flush
        ByteBuf reqMessage=null;
        for(int i=0;i<1000;i++){
            reqMessage = Unpooled.buffer(req.length);
            reqMessage.writeBytes(req);
            ctx.writeAndFlush(reqMessage);
            System.out.println(reqMessage.toString());
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
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());

        //客户端相同次数 内容打印
        System.out.println("Now is: " + body+"; the counter is: "+ ++counter);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}