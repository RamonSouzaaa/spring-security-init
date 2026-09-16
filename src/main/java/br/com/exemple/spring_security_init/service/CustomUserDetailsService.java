package br.com.exemple.spring_security_init.service;

import br.com.exemple.spring_security_init.domain.model.User;
import br.com.exemple.spring_security_init.repository.UserRepository;
import br.com.exemple.spring_security_init.infra.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Usuário não encontrado");
        return new UserSecurity(user);
    }
}

