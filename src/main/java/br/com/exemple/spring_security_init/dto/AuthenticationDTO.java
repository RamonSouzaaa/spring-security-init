package br.com.exemple.spring_security_init.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO (@NotBlank(message = "Username não pode ser vazio") 
                                  String username,
                                  @NotBlank(message = "Senha não pode ser vazio")
                                  String password ){ }
