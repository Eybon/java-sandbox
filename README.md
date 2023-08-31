# :bricks: Projet java-sandbox

Projet de test de framework/outil Java/spring


## Module springboot-loadbalancer

Test de la librairie "spring-cloud-starter-loadbalancer" pour tester le loadbalancing des clients vers plusieurs instances.

**Documentation** :
- https://www.baeldung.com/spring-cloud-load-balancer
- https://spring.io/guides/gs/spring-cloud-loadbalancer/

**Configuration applicative pour les appels sur plusieurs instances**:
```yaml
sandbox:
  loadbalancer:
    client:
      endpoints:
        - instance: 1
          url: localhost
          port: 9080
        - instance: 2
          url: localhost
          port: 9081
```

**Endpoint de test**:
```
GET localhost:8081/api/formation/springboot-loadbalancer
```

**Tests avec les wiremocks** :
- Démarrer l'application "springboot-loadbalancer" (Runner intellij : `Run LoadbalancerApplication`)
- Démarrer les wiremocks sur les port 8090 et 8091 
- Tester avec l'API /api/formation/springboot-loadbalancer


## Module springboot-wiremock

Test de la librairie "spring-cloud-contract-wiremock"

Launchers configurés sur les différents ports :
- Port 8090 -> `Run Wiremock - Port 9080`
- Port 8091 -> `Run Wiremock - Port 9081`