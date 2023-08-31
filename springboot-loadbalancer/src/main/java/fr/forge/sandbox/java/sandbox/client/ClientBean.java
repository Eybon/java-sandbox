package fr.forge.sandbox.java.sandbox.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@LoadBalancerClient(name = "client-service", configuration = ClientInstanceBean.class)
public class ClientBean {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(ClientConfiguration clientConfiguration) {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(clientConfiguration.getConnectionTimeout()))
                .setReadTimeout(Duration.ofMillis(clientConfiguration.getRequestTimeout()))
                .build();
    }
}
