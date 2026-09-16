# ⏻ Spring Boot Security

Repositório de exemplo/estudo sobre framework spring boot, implementando o registro, geração de token, controle de token e login de uma API.

** Dados do projeto **

- Java: JDK 25
- Maven: 4.0.0
- Spring Boot: 4.1.1

## 📂 Estrutura do projeto

O projeto foi construído utilizando os padrões de projeto spring boot, organizando as classes por suas respectivas responsabilidades de arquitetura:

```text
src/
└── main/
    └── java/
		└── br/
			└── com/
				└── exemple/
					└── spring_security_init/
						└── controller/
							├── AuthenticationController.java				# Controlador de requisições de acesso a API (login, register)
							├── HelloWorldController.java					# Acesso a API após login
						└── domain/
							└── enum/
								├── UserRole.java							# Enum de controle de roles de usuários 
							└── model/
								├── User.java								# Entity da tabela de usuários para registro e acesso a API
						└── dto/
							├── AuthenticationDTO.java						# Classe de dados para recebimento de dados de login
							├── LoginResponseDTO.java						# Classe de dados para resposta com o token de acesso
							├── RegisterDTO.java							# Classe de dados para recebimento de dados de registros
						└── infra/
							└── exception/
								├── GlobalExceptionHandler.java				# Controlador para interceptar e retornar de forma organizada os erros da API
							└── security/
								├── AuthenticationConfigurations.java		# Configurador de beans para autenticação da API
								├── SecurityConfigurations.java				# Configurador de beans para filtro de requisições da API
								├── SecurityFilter.java						# Componente de filtro da API para verificar a autenticação
								├── UserSecutiry.java						# Modelo da entity de usuário personalizado para autenticação
						└── repository/
							├── UserRepository.java							# Repositório para entity de usuário
						└── service/
							├── AuthenticationService.java					# Serviço para processos de login e registro
							├── CustomUserDetailsService.java				# Serviço gerênciado pelo Spring para verificar o usuário no banco de dados
							├── TokenService.java							# Serviço para processos de geração de token e verificação de token
						├── SpringSecurityInitApplication.java				# Inicializador da aplicação
```

## 🛠️ Dependências do projeto

Para esse projeto foi utilizado as depedências abaixo:

- Spring Security
- Spring Data JPA
- Spring Web
- Lombok
- Validation
- Auth JWT
- PostgreSQL Driver

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-webmvc</artifactId>
	</dependency>
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
	<dependency>
		<groupId>com.auth0</groupId>
		<artifactId>java-jwt</artifactId>
		<version>4.6.1</version>
	</dependency>
</dependencies>
```

