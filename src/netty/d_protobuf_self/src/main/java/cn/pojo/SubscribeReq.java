package cn.pojo;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/7/17 16:20
 */
public class SubscribeReq implements Serializable {

    private static final long serialVersionUID = 1304457457990177680L;
    //订购id
    private  int subReqID;

    //订购人
    private String  name;

    public SubscribeReq(int subReqID){
        this.subReqID=subReqID;
        this.name="MarsCHOU";
    }


    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "subReqID=" + subReqID +
                ", name='" + name + '\'' +
                '}';
    }
}
