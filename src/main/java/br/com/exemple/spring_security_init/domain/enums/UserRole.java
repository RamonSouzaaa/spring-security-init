package br.com.exemple.spring_security_init.domain.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user");
    
    private String role;
    
    UserRole(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return this.role;
    }
}
