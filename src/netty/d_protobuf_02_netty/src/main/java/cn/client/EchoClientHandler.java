package cn.client;


import cn.proto.SubscribeReqProto;
import cn.proto.SubscribeRespProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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
            SubscribeReqProto.SubscribeReq req = createSubscribeReq(i);
            ctx.writeAndFlush(req);
            System.out.println("client req: req(reqId="+req.getSubReqID()+";name="+req.getName()+")");

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
        SubscribeRespProto.SubscribeResp resp= ( SubscribeRespProto.SubscribeResp) msg;

        //客户端相同次数 内容打印
        System.out.println("client recevied: resp(reqId="+ resp.getSubReqID()+";code"+ resp.getReqCode()+";subscribe="+resp.getSubscribe()+"); the counter is: "+ ++counter);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {

        //客户端相同次数 内容打印
        System.out.println("client recevied:  " + msg+"; the counter is: "+ ++counter);
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

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(int reqId){
        /**
         * 通过静态方法newBuilder创建Builder实例，通过Builder构建器对SubscribeReq的属性进行设置
         */
        SubscribeReqProto.SubscribeReq.Builder builder= SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(reqId);
        builder.setName("MarsCHOU2");

        return builder.build();

    }

}