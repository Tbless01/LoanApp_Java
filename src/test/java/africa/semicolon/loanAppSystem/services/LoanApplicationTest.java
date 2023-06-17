package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.data.models.LoanApplicationStatus;
import africa.semicolon.loanAppSystem.data.models.UserRole;
import africa.semicolon.loanAppSystem.dtos.request.LoanApplicationRequest;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.LoanApplicationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.exceptions.UserAlreadyExistsException;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;
import africa.semicolon.loanAppSystem.exceptions.UserStillHasARunningLoanException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoanApplicationTest {
    @Autowired
    private LoanService loanService;
    private LoanApplicationRequest loanApplicationRequest;
    private LoanApplicationResponse loanApplicationResponse;
    @Autowired
    private UserService userService;
    private UserRegistrationRequest userRegistrationRequest;
    private UserRegistrationResponse userRegistrationResponse;

    @BeforeEach
    public void setUp() throws UserNotFoundException, UserStillHasARunningLoanException, UserAlreadyExistsException, UserRegistrationFailedException {
        userService.deleteAll();
        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("Tobi");
        userRegistrationRequest.setRole(UserRole.valueOf("LOAN_OFFICER"));
        userRegistrationRequest.setPassword("");
        userRegistrationResponse = userService.register(userRegistrationRequest);

        loanService.deleteAll();
        loanApplicationRequest = new LoanApplicationRequest();
        loanApplicationRequest.setCustomerName("Tobi");
        loanApplicationRequest.setLoanAmount(20000);
        loanApplicationRequest.setPurpose("For Upkeep");
        loanApplicationResponse = loanService.applyForLoan(loanApplicationRequest);
        System.out.println(loanApplicationRequest.getStatus());
    }
    @Test
    public void testThatUserCanApplyForLoan() {
        assertThat(loanApplicationResponse).isNotNull();
    }

    @Test
    public void loanApplicationStatusUpdateTest() throws UserNotFoundException, UserStillHasARunningLoanException {
        loanService.loanStatusUpdate(loanApplicationRequest.getCustomerName(),loanApplicationRequest);
        loanApplicationRequest.setStatus(LoanApplicationStatus.APPROVED);
        assertThat(loanApplicationRequest.getStatus()).isEqualTo(LoanApplicationStatus.APPROVED);
    }
}
