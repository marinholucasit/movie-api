# Movie-core API

## Como Rodar a Aplicação

### Pré-requisitos
- **Java 17** ou superior
- **Gradle**

### Compilar e Rodar a Aplicação
```sh
./gradlew clean build
./gradlew bootRun
```

### Rodar testes de integração
```sh
./gradlew test
```

### Testar a API
Após rodar a aplicação, acesse no navegador ou via **Postman**:
  ```sh
  curl -X GET http://localhost:8080/api/v1/movies/awards
  ```

