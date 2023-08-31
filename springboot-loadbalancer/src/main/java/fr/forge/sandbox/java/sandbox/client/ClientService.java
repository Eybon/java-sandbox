package fr.forge.sandbox.java.sandbox.client;

import fr.forge.sandbox.java.sandbox.model.Formation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class.getName());

    private final RestTemplate restTemplate;

    public ClientService(RestTemplate solrRestTemplate) {
        this.restTemplate = solrRestTemplate;
    }

    /**
     * Les appels vers les différentes instances sont gérés par spring-cloud-loadbalancer.
     * <p>
     * Attention dans l'url, "client-service" correspond au serviceId utilisé par spring-cloud-loadbalancer
     * Il est remplacé par la combinaison url:port via la classe ClientInstanceListSupplier
     */
    public Formation appelRestClient() {
        try {
            return restTemplate.getForObject(
                    UriComponentsBuilder
                            .fromHttpUrl("http://client-service/api/mock/formation")
                            .encode()
                            .toUriString(),
                    Formation.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
