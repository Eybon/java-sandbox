package fr.forge.sandbox.java.sandbox.client;

import fr.forge.sandbox.java.sandbox.model.Formation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ClientService clientService;

    @Test
    void test_basic_appelRestClient() {
        // Given
        Formation formationMock = Formation.builder()
                .id(12)
                .nom("Nom Formation")
                .description("Description Formation")
                .build();
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any()))
                .thenReturn(formationMock);

        // When
        Formation resultat = clientService.appelRestClient();

        // Then
        assertThat(resultat).isNotNull();
        assertThat(resultat.getNom()).isEqualTo("Nom Formation");
    }
}
