package vn.edu.hcmuaf.fit.security;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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

    public static String encrypt(String data, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] output = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(output);
    }

    public static String decrypt(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] output = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(output, StandardCharsets.UTF_8);
    }
    public static PublicKey getPublicKeyFromString(String publicKeyString)  {

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);


        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            try {
                return keyFactory.generatePublic(keySpec);
            } catch (InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
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
                // TODO Auto-generated catch block
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            return null;
        }

    }


}