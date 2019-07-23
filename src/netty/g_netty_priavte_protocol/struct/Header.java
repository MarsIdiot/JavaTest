package netty.g_netty_priavte_protocol.struct;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Date: 2019/7/22 16:29
 */
public class Header  implements Serializable {
    private static final long serialVersionUID = 9036068026280687403L;

    private int crcCode=0xabef0101;

    private int length;//消息长度

    private long sessionID ;//会话ID

    /**
     * 消息类型
     * (0:业务请求消息;1:业务响应消息;
     * 2:业务ONE WAY消息(既是请求又是响应消息);
     * 3:握手请求消息;4:握手应答消息;
     * 5:心跳请求消息;6:心跳答应消息：)
     */
    private byte  type;

    private byte priority;//消息优先级:0~255

    private Map<String,Object>  attachment=new HashMap<>();//附件


    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                '}';
    }
}
