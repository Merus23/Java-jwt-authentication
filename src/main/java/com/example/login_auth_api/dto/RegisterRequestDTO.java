package com.example.login_auth_api.dto;

import java.util.List;

public record RegisterRequestDTO(String name, String email, String password, List<String> roles) {
}
