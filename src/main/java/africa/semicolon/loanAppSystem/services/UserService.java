package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.data.models.UserRole;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.DeleteResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserResponse;
import africa.semicolon.loanAppSystem.exceptions.UserAlreadyExistsException;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;

import java.util.List;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationFailedException, UserAlreadyExistsException;

    UserResponse getUserById(Long id) throws UserNotFoundException;

    UserResponse getUserByUsername(String username) throws UserNotFoundException;
    List<UserResponse> getAllUsers();
    List<UserResponse> getUsersByRole(UserRole role);
//    boolean getLoanOfficerByRole(UserRole role) throws UserNotFoundException;

    DeleteResponse deleteUser(Long id);
    void deleteAll();

}


