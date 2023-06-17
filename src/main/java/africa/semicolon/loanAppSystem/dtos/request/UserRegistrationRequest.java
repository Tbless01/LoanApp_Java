package africa.semicolon.loanAppSystem.dtos.request;

// User.java

import africa.semicolon.loanAppSystem.data.models.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String username;
    private String password;
    private UserRole role;
}


