package fr.forge.sandbox.java.sandbox.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sandbox.loadbalancer.client")
@RefreshScope
public class ClientConfiguration {
    private int connectionTimeout;
    private int requestTimeout;
    private List<EndpointClientConfiguration> endpoints;
}

@Getter
@Setter
class EndpointClientConfiguration {
    private String instance;
    private String url;
    private int port;
}
