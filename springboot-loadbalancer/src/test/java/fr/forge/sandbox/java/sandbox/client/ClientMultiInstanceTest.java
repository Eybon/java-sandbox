package fr.forge.sandbox.java.sandbox.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ClientConfiguration.class)
// On utilise un fichier .properties pour la conf des tests car les visiblement les fichiers .yml
// ne sont pas supportés par l'annotation @TestPropertySource
@TestPropertySource("classpath:application-test.properties")
class ClientMultiInstanceTest {

    @Autowired
    ClientConfiguration clientConfiguration;

    @Test
    void test_basic_clientMultiInstance() {
        // When
        ClientInstanceListSupplier multiInstance = new ClientInstanceListSupplier("test-service", clientConfiguration);

        // Then (Verification Multi-instance)
        List<ServiceInstance> listInstance = Objects.requireNonNull(multiInstance.get().collectList().block()).get(0);
        assertThat(listInstance).hasSize(2);
        assertThat(listInstance.get(0).getInstanceId()).isEqualTo("test-service1");
        assertThat(listInstance.get(0).getHost()).isEqualTo("url-test-1");
        assertThat(listInstance.get(0).getPort()).isEqualTo(8095);
        assertThat(listInstance.get(1).getInstanceId()).isEqualTo("test-service2");
        assertThat(listInstance.get(1).getHost()).isEqualTo("url-test-2");
        assertThat(listInstance.get(1).getPort()).isEqualTo(9095);

        // Then (Verification Chargement config)
        assertThat(clientConfiguration.getRequestTimeout()).isEqualTo(5000);
        assertThat(clientConfiguration.getConnectionTimeout()).isEqualTo(5000);
        assertThat(clientConfiguration.getEndpoints().get(0).getInstance()).isEqualTo("1");
        assertThat(clientConfiguration.getEndpoints().get(0).getUrl()).isEqualTo("url-test-1");
        assertThat(clientConfiguration.getEndpoints().get(0).getPort()).isEqualTo(8095);
        assertThat(clientConfiguration.getEndpoints().get(1).getInstance()).isEqualTo("2");
        assertThat(clientConfiguration.getEndpoints().get(1).getUrl()).isEqualTo("url-test-2");
        assertThat(clientConfiguration.getEndpoints().get(1).getPort()).isEqualTo(9095);
    }
}
