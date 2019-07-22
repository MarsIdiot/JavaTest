package netty.g_netty_priavte_protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

/**
 * @Description:
 * @Date: 2019/7/22 17:10
 */
public class MyMarshallingEncoder  extends MarshallingEncoder {

    private static DefaultMarshallerProvider provider;
    public MyMarshallingEncoder() {
        super(MyMarshallingEncoder.provider);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        super.encode(ctx, msg, out);
    }
}
