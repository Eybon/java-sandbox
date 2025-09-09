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


## Module springboot-jasypt

Test des méthodes d'encryptage/decryptage en AES-256 via la librairie jasypt.

**Fonctionnement** :
- L'algorithm AES-256 fonctionne avec une secretKey qui doit être généré en préalable de l'encryptage/decryptage (équivalent d'un password)
- Lors d'encryptage, on fournit une chaine de caractère (String) qui sera encrypté, puis encodé en Base64 pour pouvoir être exposé/stocké
- Lors du décryptage, on fournit une chaine de caratère (String) encodé en Base64, qu'on décodera puis décryptera pour récupérer la valeur d'origine

**Workflow** :
- Génération d'une secretKey (password)
  - Soit via l'API http://localhost:8080/api/security/aes/generateKey
  - Soit en ligne, par exemple : https://www.digitalsanctuary.com/aes-key-generator-free
- Mise à jour de la secretKey dans la config `application.yml`
- Encryptage via l'API http://localhost:8080/api/security/aes/encrypt?data=toEncrypt
- Decryptage via l'API http://localhost:8080/api/security/aes/decrypt?data=toDecyptBase64
 