package it.epicode.ProgettoSettimanale_S7_L5.auth.authorization;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
