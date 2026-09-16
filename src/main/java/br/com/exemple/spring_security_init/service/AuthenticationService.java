package br.com.exemple.spring_security_init.service;

import br.com.exemple.spring_security_init.dto.AuthenticationDTO;
import br.com.exemple.spring_security_init.dto.LoginResponseDTO;
import br.com.exemple.spring_security_init.dto.RegisterDTO;
import br.com.exemple.spring_security_init.domain.enums.UserRole;
import br.com.exemple.spring_security_init.domain.model.User;
import br.com.exemple.spring_security_init.repository.UserRepository;
import br.com.exemple.spring_security_init.infra.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    
    
    public LoginResponseDTO login(AuthenticationDTO data){
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.username(),
                                                                                                   data.password()));
        UserSecurity user = (UserSecurity) auth.getPrincipal();
        String token = this.tokenService.generate(user);

        return new LoginResponseDTO(token);
    }

    public void register(RegisterDTO data) throws ResponseStatusException {
        if(this.repository.findByUsername(data.username()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já está em uso");
        }
        
        this.repository.save(new User(data.name(),
                                      data.username(),
                                      this.passwordEncoder.encode(data.password()),
                                      UserRole.valueOf(data.role())));
    }
}
