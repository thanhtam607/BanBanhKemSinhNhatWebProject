package vn.edu.hcmuaf.fit.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RSA {

    public static KeyPair genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    public static PrivateKey genPrivateKey(KeyPair keyPair) {
        return keyPair.getPrivate();
    }

    public static PublicKey genPublicKey(KeyPair keyPair) {
        return keyPair.getPublic();
    }

    // giải mã bằng public

    public static String decrypt(String data, PublicKey publicKey) throws Exception {
        byte[] byteCipherText = {};
        byte[] byteCipherAESKey = {};
        byte[] byteCipherDataAndKey = Base64.getDecoder().decode(data);
        if(byteCipherDataAndKey.length > 256){
            byteCipherText = Arrays.copyOfRange(byteCipherDataAndKey, 0, byteCipherDataAndKey.length - 256);
            byteCipherAESKey = Arrays.copyOfRange(byteCipherDataAndKey, byteCipherDataAndKey.length - 256, byteCipherDataAndKey.length);
        }


        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] byteAESKey = rsaCipher.doFinal(byteCipherAESKey);
        SecretKeySpec aesKey = new SecretKeySpec(byteAESKey, "AES");


        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);

        return new String(bytePlainText, StandardCharsets.UTF_8);
    }



    // mã hoá bằng private key

    public static String encrypt(String data, PrivateKey privateKey) throws Exception {
        // Tạo khóa AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey aesKey = keyGen.generateKey();

        // Mã hóa AES
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] byteDataToEncrypt = data.getBytes(StandardCharsets.UTF_8);
        byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);

        // Mã hóa mà private key
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] byteCipherAESKey = rsaCipher.doFinal(aesKey.getEncoded());

        byte[] byteCipherDataAndKey = new byte[byteCipherText.length + byteCipherAESKey.length];
        System.arraycopy(byteCipherText, 0, byteCipherDataAndKey, 0, byteCipherText.length);
        System.arraycopy(byteCipherAESKey, 0, byteCipherDataAndKey, byteCipherText.length, byteCipherAESKey.length);

        return Base64.getEncoder().encodeToString(byteCipherDataAndKey);
    }
    public static PublicKey getPublicKeyFromString(String publicKeyString) {

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            try {
                return keyFactory.generatePublic(keySpec);
            } catch (InvalidKeySpecException e) {
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    public static PrivateKey getPrivateKeyFromString(String privateKeyString) {

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyString);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            try {
                return keyFactory.generatePrivate(keySpec);
            } catch (InvalidKeySpecException e) {
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    //    Hash Order
    public static String hashObject(Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        String combinedString = obj.toString();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(combinedString.getBytes());
            byte[] digest = md.digest();

            for (byte b : digest) {
                stringBuilder.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
    public static boolean areKeyPairsMatching(String privateKey, String publicKey) {
        String cy = null;
        String pl = null;
        try {
            cy = encrypt("Hel", getPrivateKeyFromString(privateKey));
             pl = decrypt(cy, getPublicKeyFromString(publicKey));
        } catch (Exception e) {
           return false;
        }

        return pl.equals("Hel");
    }
    public static boolean areCypherText(String cypherTxt, String publicKey) {
        String pl = null;
        try {
             pl = decrypt(cypherTxt, getPublicKeyFromString(publicKey));
             return true;
        } catch (Exception e) {
           return false;
        }

    }

    public static void main(String[] args) throws Exception {

    }
}