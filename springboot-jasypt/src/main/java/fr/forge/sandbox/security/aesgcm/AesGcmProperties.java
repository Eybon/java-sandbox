package fr.forge.sandbox.security.aesgcm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sandbox.security.aes-gcm")
public class AesGcmProperties {
    private String secretKey;
    private String iv;
    private int tagLength;
}
