package fr.forge.sandbox.security.aesgcm;

import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class AesGcmEncryptor {

    public static final String AES_ALGORITHM = "AES/GCM/NoPadding";
    private final AesGcmProperties properties;

    public AesGcmEncryptor(AesGcmProperties properties) {
        this.properties = properties;
    }

    public String encrypt(String dataToEncrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // Initialisation du Cipher
        Cipher cipher = this.initCipher(Cipher.ENCRYPT_MODE);

        // Encryptage
        String result = this.base64Encode(cipher.doFinal(dataToEncrypt.getBytes()));
        System.out.println("Encryptage : " + result);

        return result;
    }

    public String decrypt(String dataToDecrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // Initialisation du Cipher
        Cipher cipher = this.initCipher(Cipher.DECRYPT_MODE);

        // Decryptage
        String result = new String(cipher.doFinal(this.base64Decode(dataToDecrypt)));
        System.out.println("Decryptage : " + result);

        return result;
    }

    private Cipher initCipher(int cipherMode) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        // Récupération de la secretKey (password)
        SecretKey secretKey = new SecretKeySpec(this.getBytes(properties.getSecretKey()), "AES");

        // Initialisation du Cipher pour l'encryptage AES
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(properties.getTagLength(), this.getBytes(properties.getIv()));
        cipher.init(cipherMode, secretKey, gcmParameterSpec);

        return cipher;
    }

    private String base64Encode(byte[] toEncode) {
        return Base64.getEncoder().encodeToString(toEncode);
    }

    private byte[] base64Decode(String toDecode) {
        return Base64.getDecoder().decode(toDecode);
    }

    private byte[] getBytes(String value){
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
