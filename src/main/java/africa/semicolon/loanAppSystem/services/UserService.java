package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserResponse;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;

import java.util.List;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationFailedException;

    UserResponse getUserId(Long id) throws UserNotFoundException;
    List<UserResponse> getAllUsers();


}
