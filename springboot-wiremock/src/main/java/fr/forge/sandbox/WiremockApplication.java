package fr.forge.sandbox;

import com.github.tomakehurst.wiremock.core.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AutoConfigureWireMock()
public class WiremockApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WiremockApplication.class, args);
    }

    @Value("${sandbox.wiremock.port}")
    int wiremockPort;

    @Bean
    public Options wireMockOptions() {
        return WireMockSpring
                .options()
                .port(wiremockPort);
    }
}