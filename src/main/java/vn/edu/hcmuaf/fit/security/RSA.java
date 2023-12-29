package vn.edu.hcmuaf.fit.security;

import vn.edu.hcmuaf.fit.model.SignUser;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.InvalidKeyException;
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


// giải mã bằng public
    public static String decrypt(String data, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] output = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(output);
    }
// mã hoá bằng private key
    public static String encrypt(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
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
    public static String hashOrder(String data) throws NoSuchAlgorithmException {
        if (data == null) return null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] output = md.digest(data.getBytes());
        BigInteger num = new BigInteger(1, output);
        return num.toString(16);
    }
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
    public static String sign(String hash, String pvKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        try {
            privateSignature.initSign(getPrivateKeyFromString(pvKey));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        privateSignature.update(hash.getBytes());
        byte[] signature = privateSignature.sign();
        if (signature[0] == 0) {
            byte[] tmp = new byte[signature.length - 1];
            System.arraycopy(signature, 1, tmp, 0, tmp.length);
            signature = tmp;
        }
        return new BigInteger(1, signature).toString(16);
    }
    public static boolean verify(String hash, String signature, String pbKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        try {
            publicSignature.initVerify(getPublicKeyFromString(pbKey));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        publicSignature.update(hash.getBytes());
        byte[] signatureBytes = new BigInteger(signature, 16).toByteArray();
        if (signatureBytes.length < 256 / 8) {
            byte[] tmp = new byte[256 / 8];
            System.arraycopy(signatureBytes, 0, tmp, tmp.length - signatureBytes.length, signatureBytes.length);
            for (int i = 0; i < tmp.length - signatureBytes.length; i++)
                tmp[i] = 0;
            signatureBytes = tmp;
        }
        return publicSignature.verify(signatureBytes);
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        RSA rsa = new RSA();
        SignUser su = new SignUser("aa", "bb", "cc", "dd", "ee", 1);
        String hash = rsa.hashObject(su);
        System.out.println(hash);
        String pv = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDNwpKHaRmD6GsUy3LYqk4GZDo19xNkosw8DmK0DMGrUp2Qf/YRp1IkPVJNzuLmUNUFaBFAiqjIAWn2bP7xHVL/DX2HvLp8PTyA/8UQ5ZiWkWggm8m8oVKF62CyBvLZnf1TphnINWSeBuiYJ23q4NU4rL3H9SIY2ixdkk0XnORCCF+qFOsTqUnyp2JqIHnHkxnxg7WNHtLtWh2tPT4cuy2F4qy9yVN08xU+dG2P92eXD8NAfHj/eCOdIT5jmdnvYI3DiwgOHEF5gMwtpqbthGkYRKyipQy9Sx+Ivs5+NRWHC6og/y7ef4+Ni/On8r66Qo16L1a4eOauBmQSzmdOJhbrAgMBAAECggEABHGOnIyxLQoYlesMNeWnqGJYZ3tIg0SIyJs+7R3WvjkbMgMGxhF+UqNduFR0I1Pc5dr+KbiqNcziBEe3pxliW3kScZtVI628NW+l9TF1ftBGbBMxOM5Qa+1OD0owdcarBo1wbRnKFyeVtQN/pNzqKJPXyA5hMswzZ79AbvbHxIKZNIq7STe2OvPtg9uq2LcK9EwdrZYAaDJU1pq5k1ZFkMQEzKIAAo9rbnEzKXeQ7t+AYvSBat6JWxDRkU2zxuf5Pdup0Qy0ItDPjKhGkxjIwFNH2fwudhH7JZsYFbuOp53R0ETtKc32HJLtUEyzztRGS+He4+RkOJhdQ9W4c9ob8QKBgQDlw0syj1cU+CSvjufMb2yyoEFf/i9rPeypqe8oMthv6EPB1zXpjB5qdxsc4QMtf/hMZpbOLd7JTh03nd58Hb+25caHyzm/+mjsx0GpdH/9S4MjZPiEsyXHGpU4Rhg5I1kqFsV8xYkHeliAHa8iYyyV9N19WQpCkGd7TlhsYpaILwKBgQDlQZllzi3j8khAVxaqYZNZz47o2G3HRchtc+OTys15qOkI7clwj5TCUU2wqxWOa2wBzVDMkyURqxxFF562lEvNAkLullr+/afnk0iM9fAR4nSSTAR6IH6ZQjnr7O7ZKZxLkd6EJLKJi7fAnNTGRIA9MKO04PLmvaaIBeFeW9fyBQKBgE+f9pd3BX7G4C/XHo3Cr7DXyNAzjstjknjZ43/S3x8pJxIuOwjM/l13GXZQpIFIMAyKIQki7GpnH8j+dPQeeyovRzYYP9NAPMyR4TeQoCpv2OqTO20BiL4PgFcYM5zZmzTy3032Rx7IT5NWtjL8m578VSXzjZKz6Kja5dnp8LERAoGAMz5zhF8OwOnyHneY+GKxJh0P65odB7Lgdx61Wt61CSGpXTaRlVhyvvCfdgL34Yq/bLOxANlPKuDdg9AgyNzjCKMdxOjU1DRsqIWybNe4XrcIqX7WbVoAEk/MVKD0TpRPfFDRtSXixn31h9dGdD7xjzvlC9RQVyVDKRpvYsESxuUCgYB4b5vxl2DDcekrtYyE2mbJe5BSBzMoFNUSGlcY8ZlrN66P5RnA95ztEPqUBuz/qJ76iBg5MOaQl7KuoDyG1S14unlrY3OZllxDkIPIxzpM5NsJDOzfijta71PM2NqmjIuLLtYEDJYwOYoIjxuOoetQNe7R/IxsYHS2SsJ9juoJjQ==";
        System.out.println(rsa.sign(hash, pv));
        String pb = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzcKSh2kZg+hrFMty2KpOBmQ6NfcTZKLMPA5itAzBq1KdkH/2EadSJD1STc7i5lDVBWgRQIqoyAFp9mz+8R1S/w19h7y6fD08gP/FEOWYlpFoIJvJvKFShetgsgby2Z39U6YZyDVkngbomCdt6uDVOKy9x/UiGNosXZJNF5zkQghfqhTrE6lJ8qdiaiB5x5MZ8YO1jR7S7VodrT0+HLstheKsvclTdPMVPnRtj/dnlw/DQHx4/3gjnSE+Y5nZ72CNw4sIDhxBeYDMLaam7YRpGESsoqUMvUsfiL7OfjUVhwuqIP8u3n+PjYvzp/K+ukKNei9WuHjmrgZkEs5nTiYW6wIDAQAB";
        System.out.println(rsa.verify(hash, rsa.sign(hash, pv), pb));
    }
}