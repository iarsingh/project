package com.naukrisetu.payload;

import lombok.Data;
import java.util.Set;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Set<String> roles;
} 