package app.clutch;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author yangzhao
 * @Description
 * @Date create by 15:49 18/2/3
 */
public class AESUtils {
    /**
     * 加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String SKEY = "@#clutch@#202008";
    private static String IVPARAMETER = "5651253578";

    // 加密
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String sSrc, String random) {
        Cipher cipher = null;
        String result = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] raw = SKEY.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            String ivStr = IVPARAMETER + random;
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            //此处使用BASE64做转码。
            result = Base64.getEncoder().encodeToString(encrypted);

            //result = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 解密
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String sSrc, String random) {
        try {
            byte[] raw = SKEY.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String ivStr = IVPARAMETER + random;
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
}
