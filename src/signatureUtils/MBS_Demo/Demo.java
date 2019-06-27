package signatureUtils.MBS_Demo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Demo {
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String ENCODE_ALGORITHM = "SHA-256";
    private static final String KEY_ALGORITHM = "RSA";
    public static void main(String[] args) throws Exception {
//        SysInit.getMe().init();
        test();
        File f=new File("src\\signatureUtils\\MBS_Demo\\test.txt");
        FileInputStream in = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i;
        while ((i = in.read()) != -1) {
            out.write(i);
        }
        in.close();
        byte[] key = out.toByteArray();
        out.close();
        MessageDigest messageDigest;
        String signedData = null;
        try {
            /*算法伪代码：BASE64(SIGN(MD5(所有LIST节点去空格、换行符拼接为字符串)))
              SIGN算法：SHA256withRSA
            */
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update(md5(key).getBytes());
            byte[] outputDigest_sign = messageDigest.digest();
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(getPrivateKey());
            Sign.update(outputDigest_sign);
            signedData = new BASE64Encoder().encode(Sign.sign());
            System.out.println(signedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void test() throws Exception {
        StringBuffer xmlbuff = new StringBuffer();
        StringBuffer listbuff = new StringBuffer();
        listbuff.append("<LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>国药集团化学试剂有限公司</REC_ACCT_NAME><REC_ACCT_CODE>310066797018003732011</REC_ACCT_CODE><REC_BANK_NAME>交通银行上海分行武昌路支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>-6737.45</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>12</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>南京冠华贸易有限公司</REC_ACCT_NAME><REC_ACCT_CODE>136001514010000275</REC_ACCT_CODE><REC_BANK_NAME>广发银行南京分行营业部</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>-85.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>13</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>PFIZER（国内）</REC_ACCT_NAME><REC_ACCT_CODE>136001514010000275</REC_ACCT_CODE><REC_BANK_NAME></REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>8422.92</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>14</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>北京恒业中远化工有限公司</REC_ACCT_NAME><REC_ACCT_CODE>060973012012002333</REC_ACCT_CODE><REC_BANK_NAME>交行建国路支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>1879.40</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>15</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>国药集团化学试剂有限公司</REC_ACCT_NAME><REC_ACCT_CODE>310066797018003732011</REC_ACCT_CODE><REC_BANK_NAME>交通银行上海分行武昌路支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>262254.04</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>16</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>国药集团化学试剂有限公司</REC_ACCT_NAME><REC_ACCT_CODE>310066797018003732011</REC_ACCT_CODE><REC_BANK_NAME>交通银行上海分行武昌路支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.04</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>17</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>国药集团化学试剂有限公司</REC_ACCT_NAME><REC_ACCT_CODE>310066797018003732011</REC_ACCT_CODE><REC_BANK_NAME>交通银行上海分行武昌路支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>7858.51</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>18</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>上海比欧西气体工业有限公司</REC_ACCT_NAME><REC_ACCT_CODE>404061501510018170</REC_ACCT_CODE><REC_BANK_NAME>渣打银行（中国）有限公司上海分行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>6183.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>19</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>上海实验试剂有限公司</REC_ACCT_NAME><REC_ACCT_CODE>1001260509024874144</REC_ACCT_CODE><REC_BANK_NAME>中国工商银行上海普陀联合大厦支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>100.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>20</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>上海实验试剂有限公司</REC_ACCT_NAME><REC_ACCT_CODE>1001260509024874144</REC_ACCT_CODE><REC_BANK_NAME>中国工商银行上海普陀联合大厦支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>200.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>21</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>上海海曲化工有限公司</REC_ACCT_NAME><REC_ACCT_CODE>31001656190050001142</REC_ACCT_CODE><REC_BANK_NAME>中国建设银行上海市桃浦支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>328.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>22</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>上海先导化学有限公司</REC_ACCT_NAME><REC_ACCT_CODE>044269808012425608091001</REC_ACCT_CODE><REC_BANK_NAME>中国银行上海市分行张江支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>1872.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>23</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>无锡奥灵特清洗剂科技有限公司</REC_ACCT_NAME><REC_ACCT_CODE>545658199439</REC_ACCT_CODE><REC_BANK_NAME>中国银行宜兴市支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>1370.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>24</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>常州盘固化工有限公司</REC_ACCT_NAME><REC_ACCT_CODE>32001626440050133329</REC_ACCT_CODE><REC_BANK_NAME>建行金坛支行薛埠分理处</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>380.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>25</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>烟台市牟平区康必诺化学试剂厂</REC_ACCT_NAME><REC_ACCT_CODE>90610000020100033232</REC_ACCT_CODE><REC_BANK_NAME>烟台市牟平区农村信用合作社联合社</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>750.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>26</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>浙江富盛控股集团有限公司</REC_ACCT_NAME><REC_ACCT_CODE>26316732</REC_ACCT_CODE><REC_BANK_NAME>建行常山支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>240.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>27</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>内邱县华兴化工有限公司</REC_ACCT_NAME><REC_ACCT_CODE>0406001609300032742</REC_ACCT_CODE><REC_BANK_NAME>工行内邱县支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>118</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>阜新金特莱氟化学有限责任公司</REC_ACCT_NAME><REC_ACCT_CODE>299967192983</REC_ACCT_CODE><REC_BANK_NAME>中国银行股份有限公司阜新建设广场支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>119</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>阜新恒远化工科贸有限公司</REC_ACCT_NAME><REC_ACCT_CODE>14000015201090020398</REC_ACCT_CODE><REC_BANK_NAME>阜新商业银行开发区支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>120</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>镇江市华东化工有限公司</REC_ACCT_NAME><REC_ACCT_CODE>487158201592</REC_ACCT_CODE><REC_BANK_NAME>中行江苏省金坛市支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>121</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>莱芜市润中精细化工有限公司</REC_ACCT_NAME><REC_ACCT_CODE>407673957208093001</REC_ACCT_CODE><REC_BANK_NAME>中行莱芜分行凤城东街分理处</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>122</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>浙江省黄岩人民化学厂</REC_ACCT_NAME><REC_ACCT_CODE>1207031109045022979</REC_ACCT_CODE><REC_BANK_NAME>黄岩工行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>123</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>爱斯特（成都）医药技术有限公司</REC_ACCT_NAME><REC_ACCT_CODE>51001837136051502845</REC_ACCT_CODE><REC_BANK_NAME>温江建行台商区分理处</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>0.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>124</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>国药集团化学试剂有限公司</REC_ACCT_NAME><REC_ACCT_CODE>310066797018003732011</REC_ACCT_CODE><REC_BANK_NAME>交通银行上海分行武昌路支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>-26.63</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>3751</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST><LIST><COMPANY_CODE>03010</COMPANY_CODE><ACCOUNT_CODE></ACCOUNT_CODE><REC_ACCT_NAME>无锡奥灵特清洗剂科技有限公司</REC_ACCT_NAME><REC_ACCT_CODE>545658199439</REC_ACCT_CODE><REC_BANK_NAME>中国银行宜兴市支行</REC_BANK_NAME><REC_CUR_CODE>CNY</REC_CUR_CODE><TRADE_AMOUNT>500.00</TRADE_AMOUNT><PURPOSE></PURPOSE><SERIAL>3753</SERIAL><CF_CODE></CF_CODE><BUSI_NO></BUSI_NO><TRADE_SPEED></TRADE_SPEED><PUBLIC_FLAG></PUBLIC_FLAG></LIST>");
        xmlbuff.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
        xmlbuff.append("<REQ>");
        xmlbuff.append("<BATCH_ID>10001</BATCH_ID>");
        xmlbuff.append("<SIGN_CODE>");
        xmlbuff.append(sign(listbuff.toString()));
        xmlbuff.append("</SIGN_CODE>");
        xmlbuff.append("<LISTS>");
        xmlbuff.append(listbuff.toString());
        xmlbuff.append("</LISTS>");
        xmlbuff.append("</REQ>");
        System.out.println(send(xmlbuff.toString()));
    }
    public static String sign(String srcData) {
        MessageDigest messageDigest;
        String signedData = null;
        try {
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update(encryption(srcData.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "")).getBytes());
            byte[] outputDigest_sign = messageDigest.digest();
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(getPrivateKey());
            Sign.update(outputDigest_sign);
            signedData = new BASE64Encoder().encode(Sign.sign());
            System.out.println(signedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signedData;
    }

    public static boolean verifySign(String srcData, String signedData) {
        MessageDigest messageDigest;
        boolean SignedSuccess = false;
        try {
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update(encryption(srcData.replaceAll("\r\n", "").replaceAll(" ", "")).getBytes());
            byte[] outputDigest_verify = messageDigest.digest();
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(getPublicKey());
            verifySign.update(outputDigest_verify);
            SignedSuccess = verifySign.verify(new BASE64Decoder().decodeBuffer(signedData));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SignedSuccess;
    }
    public static PublicKey getPublicKey() {
        try {
            File inFile = new File("src\\signatureUtils\\MBS_Demo\\key\\public.key");
            FileInputStream fileInputStream = new FileInputStream(inFile);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            while ((i = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(i);
            }
            fileInputStream.close();
            byte[] keyBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static PrivateKey getPrivateKey() {
        try {
            File inFile = new File("src\\signatureUtils\\MBS_Demo\\key\\private.key");
            FileInputStream fileInputStream = new FileInputStream(inFile);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            while ((i = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(i);
            }
            fileInputStream.close();
            byte[] keyBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String encryption(String s) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = s.getBytes();
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    private static String md5(byte[] byteArray) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String send(String aMsg) throws IOException {
        URL obUrl = new URL("http://www7.asuscomm.com:7201/ymkd/ERPServlet");
        StringBuffer sbBuffer = new StringBuffer();
        InputStream in = null;
        OutputStream out = null;
        HttpURLConnection obConnection = null;
        try {
            obConnection = (HttpURLConnection) obUrl.openConnection();
            obConnection.setDoInput(true);
            obConnection.setDoOutput(true);
            obConnection.setConnectTimeout(120000);
            obConnection.setRequestMethod("POST");
            out = obConnection.getOutputStream();
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "GBK");
            outWriter.write(aMsg);
            outWriter.flush();

            in = obConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "GBK"));
            char[] buf = new char[8192];
            int length = 0;
            while ((length = bufferedReader.read(buf, 0, buf.length)) != -1) {
                sbBuffer.append(buf, 0, length);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (obConnection != null) {
                obConnection.disconnect();
            }
        }
        return sbBuffer.toString();
    }
}