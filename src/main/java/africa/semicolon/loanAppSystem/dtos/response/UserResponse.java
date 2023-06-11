package africa.semicolon.loanAppSystem.dtos.response;

import africa.semicolon.loanAppSystem.data.models.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Long id;
    private String username;
    private UserRole role;
}
