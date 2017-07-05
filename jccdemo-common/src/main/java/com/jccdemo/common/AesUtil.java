package com.jccdemo.common;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 *
 */
public class AesUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String KEY = "c0e9fcff597633b8b92939a1a2724a44";

    public static byte[] initSecretKey() {
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        kg.init(128);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }


    private static Key toKey(byte[] key){
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    public static byte[] encrypt(byte[] data,Key key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }


    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }


    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }


    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }




    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }


    public static byte[] decrypt(byte[] data,Key key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }


    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private static String  showByteArray(byte[] data){
        if(null == data){
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for(byte b:data){
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String aesEncrypt(String content, String skey) throws Exception{
        byte[] key = AesUtil.parseHexStr2Byte(skey);
        Key k = toKey(key);
        byte[] encryptData = encrypt(content.getBytes(), k);
        String result = parseByte2HexStr(encryptData);
        return result;
    }
    
    //added by James
    public static String aesEncrypt(String content) throws Exception{
			return aesEncrypt(content,KEY);
    }
    //added by James
    public static String aesDecrypt(String content) throws Exception{
    	return aesDecrypt(content,KEY);
    }
    
    //added by James
    public static String aesEncryptWithIV(String key,String iv,String data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new sun.misc.BASE64Encoder().encode(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //added by James
    public static String aesDecryptWithIV(String key,String iv,String data) throws Exception {
        try
        {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
 
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String aesDecrypt(String content, String skey) throws Exception{
        byte[] key = AesUtil.parseHexStr2Byte(skey);
        Key k = toKey(key);
        byte[] encryptData = decrypt(parseHexStr2Byte(content), k);
        String result = new String(encryptData,"utf-8");
        return result;
    }

    public static void main(String[] args) throws Exception {
    	String key = "2028314237413212";
    	String  iv = "9237512912315123";
        String data = "段锟2016年10月14日";
        String result1 = aesEncryptWithIV(key,iv,data);
        System.out.println("加密后数据: " + result1);
        String result2 = aesDecryptWithIV(key,iv,result1);
        System.out.println("解密后数据: " + result2);
    	System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
