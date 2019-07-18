package cn.test;

import cn.proto.SubscribeReqProto;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Description:
 * @Date: 2019/7/18 13:42
 */
public class Test {
    /**
     * 编码encode
     *
     * 将SubscribeReq类型的转为字节数组
     * @param req
     * @return
     */
    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        /**
         * 编码时通过调用SubscribeReqProto.SubscribeReq实例的toByteArray即可将SubscribeReq编码为byte数组
         */
        return req.toByteArray();
    }

    /**
     * 解码
     * 将字节数组转换为SubscribeReq类型
     * @param body
     * @return
     * @throws InvalidProtocolBufferException
     */
    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        /**
         * 解码时通过调用SubscribeReqProto.SubscribeReq实例的静态方法parseForm将二进制byte数组解码为原始的对象
         */
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        /**
         * 通过静态方法newBuilder创建Builder实例，通过Builder构建器对SubscribeReq的属性进行设置
         */
        SubscribeReqProto.SubscribeReq.Builder builder= SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setName("圣路易斯嘎嘎");

        return builder.build();

    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req=createSubscribeReq();
        System.out.println("encode before:"+req.toString());


        SubscribeReqProto.SubscribeReq req2= decode(encode(req));
        System.out.println("after decode:"+req.toString());

        System.out.println(req2.equals(req));

    }




}
