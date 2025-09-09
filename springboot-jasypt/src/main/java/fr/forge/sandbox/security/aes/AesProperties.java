package fr.forge.sandbox.security.aes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sandbox.security.aes")
public class AesProperties {
    private String secretKey;
}
