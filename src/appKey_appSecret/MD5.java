package appKey_appSecret;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/11/26 14:08
 */
public class MD5 {
    private String appkey;
    private String appScrect;

    /**
     * UUID随机生成appKey
     */
    public  void getAppkey(){
        String appKey = UUID.randomUUID().toString();
        this.appkey=appKey;
    }

    /**
     * MD5加密生成appScrect
     */
    public  void getAppScrect() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //digest传入byte，返回byte[]字节数组
            byte[] digest = md5.digest(this.appkey.getBytes());
            //做相应的转化（十六进制）
            String MD5String = hex(digest);
            this.appScrect=MD5String;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字节数组转换为16进制字符串
     */
    public String hex(byte[] bytes){
        StringBuffer sb=new StringBuffer("");
        int i=0;//接收bytes元素
        for(int offset=0;offset<bytes.length;offset++){
            i=bytes[offset];
            if (i < 0){
                i += 256;
            }
            if (i < 16) {
                sb.append("0");
            }
            String hexString = Integer.toHexString(i);
            sb.append(hexString);
        }
        return sb.toString();
    }


    @Test
    public void  test1(){
        this.getAppkey();
        this.getAppScrect();
        System.out.println("appkey:"+this.appkey);
        System.out.println("appScrect:"+this.appScrect);


    }

}


/**
 * public String substring(int beginIndex, int endIndex)
 * 第一个int为开始的索引，对应String数字中的开始位置，
 * 第二个是截止的索引位置，对应String中的结束位置
 * 1、取得的字符串长度为：endIndex - beginIndex;
 * 2、从beginIndex开始取，到endIndex结束，从0开始数，其中不包括endIndex位置的字符
 * 如：
 * "hamburger".substring(4, 8) returns "urge"
 *  "smiles".substring(1, 5) returns "mile"
 * 取长度大于等于3的字符串a的后三个子字符串，只需a.subString(a.length()-3, a.length());
 *
 * */
