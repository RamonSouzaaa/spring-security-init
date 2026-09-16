package br.com.exemple.spring_security_init.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO (@NotBlank(message = "Nome não pode ser vazio") 
                            String name,
                            @NotBlank(message = "Usuário não pode ser vazio") 
                            String username,
                            @NotBlank(message = "Senha não pode ser vazio")
                            @Size(min = 6, message = "A senha deve ter no  mínimo 6 caracteres")
                            String password,
                            @NotBlank(message = "Role do usuário não informada")
                            String role) { }
