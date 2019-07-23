package netty.g_netty_priavte_protocol.struct;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/7/22 16:25
 */
public class NettyMessage implements Serializable {

    private static final long serialVersionUID = 3273879681449482509L;

    private Header header;

    private Object body;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
