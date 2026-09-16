package br.com.exemple.spring_security_init.repository;

import br.com.exemple.spring_security_init.domain.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> { 

    public User findByUsername(String username);
}

