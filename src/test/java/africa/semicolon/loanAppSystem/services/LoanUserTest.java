package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.data.models.UserRole;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserResponse;
import africa.semicolon.loanAppSystem.exceptions.UserAlreadyExistsException;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.math.BigInteger.*;
import static java.math.BigInteger.ONE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoanUserTest {
    @Autowired
    private UserService userService;
    private UserRegistrationRequest userRegistrationRequest;
    private UserRegistrationResponse userRegistrationResponse;

    @BeforeEach
    public void setUp() throws UserRegistrationFailedException, UserAlreadyExistsException {
        userService.deleteAll();
        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("Tobi");
        userRegistrationRequest.setRole(UserRole.valueOf("LOAN_OFFICER"));
        userRegistrationRequest.setPassword("");
        userRegistrationResponse = userService.register(userRegistrationRequest);
    }

    @Test
    public void testThatUserCanRegister() {
        assertThat(userRegistrationResponse).isNotNull();
    }

    @Test
    public void getCustomerByIdTest() throws UserRegistrationFailedException, UserAlreadyExistsException, UserNotFoundException {
        var foundUser = userService.getUserById(userRegistrationResponse.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(userRegistrationRequest.getUsername());
    }

    @Test
    public void deleteUserTest() {
        var users = userService.getAllUsers();
        int numberOfUsers = users.size();
        assertThat(numberOfUsers).isGreaterThan(ZERO.intValue());
        userService.deleteUser(userRegistrationResponse.getId());
        List<UserResponse> currentUsers = userService.getAllUsers();
        assertThat(currentUsers.size()).isEqualTo(numberOfUsers - ONE.intValue());
    }

    @Test
    public void findUserByRoleTest() {
        var user = userService.getUsersByRole(UserRole.LOAN_OFFICER);
        assertThat(user).isNotNull();
    }

}
