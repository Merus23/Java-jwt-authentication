package com.example.login_auth_api.models;

import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private List<String> roles;
    private String email;
    private String password;

    public User(String id, String name, List<String> roles, String email, String password) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public List<SimpleGrantedAuthority> getAuthorities (){
        return this.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
