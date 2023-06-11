package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.data.models.User;
import africa.semicolon.loanAppSystem.data.repository.UserRepository;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserResponse;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;
import africa.semicolon.loanAppSystem.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.loanAppSystem.utils.ExceptionUtils.USER_REGISTRATION_FAILED;
import static africa.semicolon.loanAppSystem.utils.ExceptionUtils.USER_WITH_ID_NOT_FOUND;
import static africa.semicolon.loanAppSystem.utils.ResponseUtils.USER_REGISTRATION_SUCCESSFUL;

@Service
@AllArgsConstructor
public class LoanUserService implements UserService {

    private final UserRepository userRepository;

//    private final ModelMapper modelMapper;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationFailedException {
//        User user = modelMapper.map(userRegistrationRequest, User.class);
        User user = new User();
        Mapper.map(userRegistrationRequest, user);
        User savedUser = userRepository.save(user);

        boolean isSavedUser = savedUser.getId() != null;
        if (!isSavedUser)
            throw new UserRegistrationFailedException(String.format(USER_REGISTRATION_FAILED, userRegistrationRequest.getUsername()));
        return userRegistrationResponse(savedUser.getId());
    }

    @Override
    public UserResponse getUserId(Long id) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.orElseThrow(() -> new UserNotFoundException(
                String.format(USER_WITH_ID_NOT_FOUND, id)
        ));
        UserResponse userResponse = buildUserResponse(user);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return null;
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