package it.epicode.ProgettoSettimanale_S7_L5.auth.authorization;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
