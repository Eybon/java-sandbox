package fr.forge.sandbox.security.controller;

import fr.forge.sandbox.security.aes.AesEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class SecurityController {

    private final AesEncryptor aesEncryptor;

    public SecurityController(AesEncryptor aesEncryptor) {
        this.aesEncryptor = aesEncryptor;
    }

    @GetMapping("/api/security/aes/encrypt")
    public String aesEncrypt(@RequestParam(name = "data") final String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return aesEncryptor.encrypt(data);
    }

    @GetMapping("/api/security/aes/decrypt")
    public String aesDecrypt(@RequestParam(name = "data") final String data) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return aesEncryptor.decrypt(data);
    }

    @GetMapping("/api/security/aes/generateKey")
    public String aesGenerateSecretKey() throws NoSuchAlgorithmException {
        return aesEncryptor.generateSecretKey();
    }
}
