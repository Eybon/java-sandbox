package fr.forge.sandbox.security.aes;

import fr.forge.sandbox.security.config.SecurityProperties;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class AesEncryptor {

    public static final String AES_ALGORITHM = "AES";
    private final SecurityProperties securityProperties;

    public AesEncryptor(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public String encrypt(String dataToEncrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Initialisation du Cipher
        Cipher cipher = this.initCipher(Cipher.ENCRYPT_MODE);

        // Encryptage
        String result = this.base64Encode(cipher.doFinal(dataToEncrypt.getBytes()));
        System.out.println("Encryptage : " + result);

        return result;
    }

    public String decrypt(String dataToDecrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Initialisation du Cipher
        Cipher cipher = this.initCipher(Cipher.DECRYPT_MODE);

        // Decryptage
        String result = new String(cipher.doFinal(this.base64Decode(dataToDecrypt)));
        System.out.println("Decryptage : " + result);

        return result;
    }

    private Cipher initCipher(int cipherMode) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        // Récupération de la secretKey (password)
        SecretKey secretKey = new SecretKeySpec(this.base64Decode(securityProperties.getAesSecretKey()), "AES");

        // Initialisation du Cipher pour l'encryptage AES
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(cipherMode, secretKey);

        return cipher;
    }

    public String generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGen.init(256);

        return this.base64Encode(keyGen.generateKey().getEncoded());
    }

    private String base64Encode(byte[] toEncode) {
        return Base64.getEncoder().encodeToString(toEncode);
    }

    private byte[] base64Decode(String toDecode) {
        return Base64.getDecoder().decode(toDecode);
    }
}
