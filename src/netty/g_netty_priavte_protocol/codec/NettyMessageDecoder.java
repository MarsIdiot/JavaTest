package netty.g_netty_priavte_protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import netty.g_netty_priavte_protocol.struct.Header;
import netty.g_netty_priavte_protocol.struct.NettyMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:消息解码
 * @Date: 2019/7/22 16:40
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    private MyLengthFieldBasedFrameDecoder decoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.decoder=new MyLengthFieldBasedFrameDecoder(maxFrameLength,lengthFieldOffset,lengthFieldLength);
    }


    protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
            throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

        NettyMessage message = new NettyMessage();
        Header header = new Header();

        // //header基本字段
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());

        //header附件字段
        int size = frame.readInt();
        if (size > 0) {
            Map<String, Object> attch = new HashMap<String, Object>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                frame.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attch.put(key, decoder.decode(ctx,frame));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attch);
        }

        //body字段
        if (frame.readableBytes() > 4) {
            message.setBody(decoder.decode(ctx,frame));
        }
        message.setHeader(header);
        return message;
    }
}
