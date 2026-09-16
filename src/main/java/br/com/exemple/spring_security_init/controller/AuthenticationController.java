package br.com.exemple.spring_security_init.controller;

import br.com.exemple.spring_security_init.dto.AuthenticationDTO;
import br.com.exemple.spring_security_init.dto.LoginResponseDTO;
import br.com.exemple.spring_security_init.dto.RegisterDTO;
import br.com.exemple.spring_security_init.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody
                                @Valid
                                AuthenticationDTO data) {
        return ResponseEntity.ok().body(authenticationService.login(data));
    }
    
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody
                                   @Valid
                                   RegisterDTO data) {
        this.authenticationService.register(data);
        return ResponseEntity.created(URI.create("/login")).build();
    }
}
