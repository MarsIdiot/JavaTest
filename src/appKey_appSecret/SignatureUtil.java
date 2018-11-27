package appKey_appSecret;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 接口签名验证
 * @author: xiaokang.zhu
 * @email: xiaokang.zhu@ucarinc.com
 * @date: 2018/11/26
 * @version: 1.0
 */
public class SignatureUtil {

    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String sign(Map<String, String> params, String appKey, String appSecret, String timestamp, String url) {
        Set<String> paramsKeySet = params.keySet();
        String[] keys = paramsKeySet.toArray(new String[paramsKeySet.size()]);
        Arrays.sort(keys);
        String joinedParams = joinRequestParams(params, appKey, appSecret, timestamp, url, keys);
        byte[] abstractMessage = digest(joinedParams);
        return byte2Hex(abstractMessage);
    }

    private static String byte2Hex(byte[] data) {
        int length = data.length;
        char hexChars[] = new char[length * 2];
        int index = 0;
        for (byte value : data) {
            hexChars[index++] = HEX_DIGITS[value >>> 4 & 0xf];
            hexChars[index++] = HEX_DIGITS[value & 0xf];
        }
        return new String(hexChars);
    }

    private static byte[] digest(String message) {
        try {
            MessageDigest md5Instance = MessageDigest.getInstance("MD5");
            md5Instance.update(message.getBytes("UTF-8"));
            return md5Instance.digest();
        } catch (Exception e) {
            throw new RuntimeException("签名验证失败", e);
        }
    }

    private static String joinRequestParams(Map<String, String> params, String appKey, String appSecret, String timestamp, String url, String[] sortedKeys) {

        StringBuilder sb = new StringBuilder();
        sb.append(timestamp); // 拼接timestamp
        sb.append(appKey); // appKey
        sb.append(appSecret); // appSecret
        sb.append(url);
        for (String key : sortedKeys) {
            String value = params.get(key);
            if (isNotEmpty(key) && isNotEmpty(value)) {
                sb.append(key).append(value);
            }
        }
        return sb.toString();
    }

    private static boolean isNotEmpty(String s) {
        return null != s && !"".equals(s);
    }
}