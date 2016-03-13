package w.p.j.vendor.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Created by everythingok on 2016/2/22.
 */
public class ThreeDes {

    private static final  String Algorithm = "DESede";

    /**
     * 3DES加密
     * @param keybyte 秘钥
     * @param src     加密数据
     * @return        加密之后的数据
     */
    public static byte[] encryptMode(byte[] keybyte,byte[] src){
        //生成秘钥
        SecretKey deskey = new SecretKeySpec(keybyte,Algorithm);

        //加密
        try {
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE,deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public static  void main(String[] args){
//        //添加新安全算法,如果用JCE就要把它添加进去
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());
//       // final byte[] keyBytes = "111111111111111111111111".getBytes();
//        String szSrc = "This is a 3DES test. 测试";
//
//        final byte[] keyBytes = {0x11, 0x22, 0x4F, 0x58, (byte)0x88, 0x10, 0x40, 0x38
//                , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
//                , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2};
//        System.out.println("加密前的字符串:" + szSrc);
//
//        byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
//        System.out.println("加密后的字符串:" + new String(encoded));
//    }

    public static String getMD5Str(String sMsg) {
        byte[] msg = sMsg.getBytes();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(msg);
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        byte[] b = messageDigest.digest();
        return new String(Base64.encodeBase64(b));
    }

    public static String GetDecodeStr(String bytes) {
        String sRes = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);

        for (int i = 0; i < bytes.length(); i += 2)
            baos.write("0123456789ABCDEF".indexOf(bytes.charAt(i)) << 4 | "0123456789ABCDEF".indexOf(bytes.charAt(i + 1)));
        try
        {
            sRes = new String(baos.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sRes;
    }

}
