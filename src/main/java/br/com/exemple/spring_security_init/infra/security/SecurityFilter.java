package br.com.exemple.spring_security_init.infra.security;

import br.com.exemple.spring_security_init.service.TokenService;
import br.com.exemple.spring_security_init.domain.model.User;
import br.com.exemple.spring_security_init.repository.UserRepository;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) 
            throws ServletException, 
                   IOException,  
                   JWTVerificationException {
        String token = this.getToken(request);
        if(token != null) {
            String username = this.tokenService.validate(token);
            User user = this.userRepository.findByUsername(username);
            
            if(user != null) {
                UserSecurity userSecurity = new UserSecurity(user);
                var authentication = new UsernamePasswordAuthenticationToken(userSecurity, null, userSecurity.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String getToken(HttpServletRequest request) { 
        String authHeader = request.getHeader("Authorization");
        if((authHeader == null) || (!authHeader.startsWith("Bearer"))) {
            return null;
        }
        
        String token = authHeader.replace("Bearer", "").replace(" ", "");
        return token;
    }
    
}
