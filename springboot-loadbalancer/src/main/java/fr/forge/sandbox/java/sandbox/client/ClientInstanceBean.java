package fr.forge.sandbox.java.sandbox.client;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

public class ClientInstanceBean {
    @Bean
    ClientInstanceListSupplier clientInstanceListSupplier(ClientConfiguration clientConfiguration) {
        return new ClientInstanceListSupplier("client-service", clientConfiguration);
    }
}

class ClientInstanceListSupplier implements ServiceInstanceListSupplier {

    private final String serviceId;
    private final ClientConfiguration clientConfiguration;

    public ClientInstanceListSupplier(String serviceId, ClientConfiguration clientConfiguration) {
        this.serviceId = serviceId;
        this.clientConfiguration = clientConfiguration;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(this.clientConfiguration.getEndpoints()
                .stream()
                .map(x -> new DefaultServiceInstance(
                        serviceId + x.getInstance(),
                        serviceId,
                        x.getUrl(),
                        x.getPort(),
                        false))
                .collect(Collectors.toList()));
    }
}
