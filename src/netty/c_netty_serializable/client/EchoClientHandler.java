package netty.c_netty_serializable.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.c_netty_serializable.server.SubscribeResp;

/**
 * @Description:
 * @Auther:
 * @Date: 2019/6/5 14:41
 */

public  class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int counter;

    /**
     * 向服务器发起消息入口
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channelActive..");
        //ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8)); // 必须有flush
        for(int i=0;i<10;i++){
            SubscribeReq req=new SubscribeReq(i);
            ctx.writeAndFlush(req);
            System.out.println("client req: "+req.toString());
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
        SubscribeResp resp= (SubscribeResp) msg;

        //客户端相同次数 内容打印
        System.out.println("Now is: " + resp.toString()+"; the counter is: "+ ++counter);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        //客户端相同次数 内容打印
        System.out.println("Now0 is: " + msg+"; the counter is: "+ ++counter);
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

}