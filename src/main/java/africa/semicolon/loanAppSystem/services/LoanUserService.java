package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.data.models.User;
import africa.semicolon.loanAppSystem.data.models.UserRole;
import africa.semicolon.loanAppSystem.data.repository.UserRepository;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.DeleteResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserResponse;
import africa.semicolon.loanAppSystem.exceptions.UserAlreadyExistsException;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;
import africa.semicolon.loanAppSystem.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.loanAppSystem.utils.ExceptionUtils.*;
import static africa.semicolon.loanAppSystem.utils.ResponseUtils.USER_DELETED_SUCCESSFULLY;
import static africa.semicolon.loanAppSystem.utils.ResponseUtils.USER_REGISTRATION_SUCCESSFUL;

@Service
@AllArgsConstructor
public class LoanUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationFailedException, UserAlreadyExistsException {
        User user = checkIfUserAlreadyExist(userRegistrationRequest);
        Mapper.map(userRegistrationRequest, user);
        User savedUser = userRepository.save(user);
        boolean isSavedUser = savedUser.getId() != null;
        if (!isSavedUser)
            throw new UserRegistrationFailedException(String.format(USER_REGISTRATION_FAILED, userRegistrationRequest.getUsername()));
        return userRegistrationResponse(savedUser.getId());
    }

    private User checkIfUserAlreadyExist(UserRegistrationRequest userRegistrationRequest) throws UserAlreadyExistsException {
        if (userExist(userRegistrationRequest.getUsername()))
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS, userRegistrationRequest.getUsername()));
        User user = new User();
        return user;
    }

    private boolean userExist(String username) {
        User foundUser = userRepository.findByUsername(username);
        return foundUser != null;
    }

    @Override
    public UserResponse getUserById(Long id) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.orElseThrow(() -> new UserNotFoundException(
                String.format(USER_WITH_ID_NOT_FOUND, id)
        ));
        UserResponse userResponse = buildUserResponse(user);
        return userResponse;
    }

    @Override
    public UserResponse getUserByUsername(String username) throws UserNotFoundException {
        Optional<User> foundUser = Optional.of(userRepository.findByUsername(username));
        User user = foundUser.orElseThrow(() -> new UserNotFoundException(
                String.format(USER_WITH_USERNAME_NOT_FOUND, username)
        ));
        UserResponse userResponse = buildUserResponse(user);
        return userResponse;
    }

    @Override
    public boolean getLoanOfficerByRole(UserRole status) {
        var foundUser = userRepository.findAUsersByRole(status);
        if (foundUser.getRole() == UserRole.LOAN_OFFICER) return true;
        return false;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(LoanUserService::buildUserResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getUsersByRole(UserRole role) {
        List<User> users = userRepository.findUsersByRole(role);
        return users.stream()
                .map(LoanUserService::buildUserResponse)
                .toList();
    }

    @Override
    public DeleteResponse deleteUser(Long id) {
        userRepository.deleteById(id);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage(USER_DELETED_SUCCESSFULLY);
        deleteResponse.setId(id);
        return deleteResponse;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    private static UserRegistrationResponse userRegistrationResponse(Long userId) {
        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setMessage(USER_REGISTRATION_SUCCESSFUL);
        userRegistrationResponse.setId(userId);
        return userRegistrationResponse;
    }

    private static UserResponse buildUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}