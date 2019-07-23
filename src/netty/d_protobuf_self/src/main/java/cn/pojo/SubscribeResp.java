package netty.d_protobuf_self.src.main.java.cn.pojo;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/7/17 16:20
 */
public class SubscribeResp implements Serializable {

    private static final long serialVersionUID = 4489891686170051461L;
    //订购id
    private  String subReqID;

    //订购结果
    private int  reqCode;

    //描述
    private String subscribe;

    public SubscribeResp(String subReqID){
        this.subReqID=subReqID;
        this.reqCode=0;
        this.subscribe="订购成功";
    }

    public String getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(String subReqID) {
        this.subReqID = subReqID;
    }

    public int getReqCode() {
        return reqCode;
    }

    public void setReqCode(int reqCode) {
        this.reqCode = reqCode;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subReqID='" + subReqID + '\'' +
                ", reqCode=" + reqCode +
                ", subscribe='" + subscribe + '\'' +
                '}';
    }
}
