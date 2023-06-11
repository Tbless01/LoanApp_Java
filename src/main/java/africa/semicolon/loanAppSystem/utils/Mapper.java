package africa.semicolon.loanAppSystem.utils;

import africa.semicolon.loanAppSystem.data.models.User;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;

public class Mapper {

    public static void map(UserRegistrationRequest userRegistrationRequest, User user){
        user.setUsername(userRegistrationRequest.getUsername());
        user.setPassword(userRegistrationRequest.getPassword());
        user.setRole(userRegistrationRequest.getRole());
    }
}
