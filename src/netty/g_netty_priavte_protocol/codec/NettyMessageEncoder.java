package netty.g_netty_priavte_protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.g_netty_priavte_protocol.struct.NettyMessage;

import java.util.Map;

/**
 * @Description:消息编码
 * @Date: 2019/7/22 16:41
 */
public class NettyMessageEncoder  extends MessageToByteEncoder<NettyMessage> {

    private MyMarshallingEncoder  myMarshallingEncoder;
    public NettyMessageEncoder(){
        this.myMarshallingEncoder = new MyMarshallingEncoder();
    }


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage msg, ByteBuf sendBuf) throws Exception {

        if (msg == null || msg.getHeader() == null)
            throw new Exception("The encode message is null");

        //header基本字段
        sendBuf.writeInt((msg.getHeader().getCrcCode()));
        sendBuf.writeInt((msg.getHeader().getLength()));
        sendBuf.writeLong((msg.getHeader().getSessionID()));
        sendBuf.writeByte((msg.getHeader().getType()));
        sendBuf.writeByte((msg.getHeader().getPriority()));
        sendBuf.writeInt((msg.getHeader().getAttachment().size()));//附件大小

        //header附件字段
        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for(Map.Entry<String, Object> param:msg.getHeader().getAttachment().entrySet()){

            //编码key
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);//key长度
            sendBuf.writeBytes(keyArray);//key值

            //编码value
            myMarshallingEncoder.encode(channelHandlerContext,value, sendBuf);

        }
        key = null;
        keyArray = null;
        value = null;

        //body字段
        if (msg.getBody() != null) {
            myMarshallingEncoder.encode(channelHandlerContext,msg.getBody(), sendBuf);
        } else{
            sendBuf.writeInt(0);
        }
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
