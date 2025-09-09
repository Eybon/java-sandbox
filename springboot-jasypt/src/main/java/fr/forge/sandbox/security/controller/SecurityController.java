package fr.forge.sandbox.security.controller;

import fr.forge.sandbox.security.aes.AesEncryptor;
import fr.forge.sandbox.security.aesgcm.AesGcmEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class SecurityController {

    private final AesEncryptor aesEncryptor;
    private final AesGcmEncryptor aesGcmEncryptor;

    public SecurityController(AesEncryptor aesEncryptor, AesGcmEncryptor aesGcmEncryptor) {
        this.aesEncryptor = aesEncryptor;
        this.aesGcmEncryptor = aesGcmEncryptor;
    }

    @GetMapping("/api/security/aes/encrypt")
    public String aesEncrypt(@RequestParam(name = "data") final String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return aesEncryptor.encrypt(data);
    }

    @GetMapping("/api/security/aes/decrypt")
    public String aesDecrypt(@RequestParam(name = "data") final String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        return aesEncryptor.decrypt(data);
    }

    @GetMapping("/api/security/aes-gcm/encrypt")
    public String aesGcmEncrypt(@RequestParam(name = "data") final String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        return aesGcmEncryptor.encrypt(data);
    }

    @GetMapping("/api/security/aes-gcm/decrypt")
    public String aesGcmDecrypt(@RequestParam(name = "data") final String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        return aesGcmEncryptor.decrypt(data);
    }

    @GetMapping("/api/security/aes/generateKey")
    public String aesGenerateSecretKey() throws NoSuchAlgorithmException {
        return aesEncryptor.generateSecretKey();
    }
}
