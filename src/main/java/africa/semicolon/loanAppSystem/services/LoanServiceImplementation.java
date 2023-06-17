package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.data.models.LoanApplication;
import africa.semicolon.loanAppSystem.data.models.LoanApplicationStatus;
import africa.semicolon.loanAppSystem.data.repository.LoanApplicationRepository;
import africa.semicolon.loanAppSystem.dtos.request.LoanApplicationRequest;
import africa.semicolon.loanAppSystem.dtos.response.LoanApplicationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UpdateResponse;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserStillHasARunningLoanException;
import africa.semicolon.loanAppSystem.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static africa.semicolon.loanAppSystem.utils.ExceptionUtils.USER_STILL_HAS_AN_UNSETTLED_LOAN;
import static africa.semicolon.loanAppSystem.utils.ExceptionUtils.USER_WITH_USERNAME_DOES_NOT_EXIST;
import static africa.semicolon.loanAppSystem.utils.ResponseUtils.LOAN_APPLICATION_SUCCESSFUL;
import static africa.semicolon.loanAppSystem.utils.ResponseUtils.LOAN_APPLICATION_UPDATED_SUCCESSFULLY;


@Service
@AllArgsConstructor
public class LoanServiceImplementation implements LoanService {

    private final UserService userService;
    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    public LoanApplicationResponse applyForLoan(LoanApplicationRequest loanApplicationRequest) throws UserNotFoundException, UserStillHasARunningLoanException {
        checkUserExistsWithUsername(loanApplicationRequest);
        if (checkIfUserHasARunningLoan(loanApplicationRequest.getCustomerName()))
            throw new UserStillHasARunningLoanException(String.format(USER_STILL_HAS_AN_UNSETTLED_LOAN));
        LoanApplication loanApplication = new LoanApplication();
        Mapper.map(loanApplicationRequest, loanApplication);
        LoanApplication loanApplicationSaved = loanApplicationRepository.save(loanApplication);
        return loanResponse(loanApplicationSaved.getId());
    }

    @Override
    public UpdateResponse loanStatusUpdate(String name, LoanApplicationRequest loanApplicationRequest) throws UserNotFoundException, UserStillHasARunningLoanException {
        var foundUser = loanApplicationRepository.findByCustomerName(name);
        foundUser.setStatus(loanApplicationRequest.getStatus());
        LoanApplication loanApplication = loanApplicationRepository.save(foundUser);
        return loanUpdate(loanApplication.getId());
    }

    @Override
    public void deleteAll() {
        loanApplicationRepository.deleteAll();
    }

    private void checkUserExistsWithUsername(LoanApplicationRequest loanRequest) throws UserNotFoundException {
        try {
            var foundUser = userService.getUserByUsername(loanRequest.getCustomerName());
        } catch (UserNotFoundException | NullPointerException e) {
            throw new UserNotFoundException(String.format(USER_WITH_USERNAME_DOES_NOT_EXIST, loanRequest.getCustomerName()));
        }
    }

    private boolean checkIfUserHasARunningLoan(String username) {
        var foundUser = loanApplicationRepository.findByCustomerName(username);
        if (foundUser != null)
            if (foundUser.getStatus().name().equalsIgnoreCase(LoanApplicationStatus.IN_PROGRESS.getStatus()) || foundUser.getStatus().name().equalsIgnoreCase(LoanApplicationStatus.APPROVED.getStatus()))
                return foundUser != null;
        return false;
    }

    private static LoanApplicationResponse loanResponse(Long userId) {
        LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();
        loanApplicationResponse.setMessage(LOAN_APPLICATION_SUCCESSFUL);
        loanApplicationResponse.setId(userId);
        return loanApplicationResponse;
    }

    private static UpdateResponse loanUpdate(Long userId) {
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setMessage(LOAN_APPLICATION_UPDATED_SUCCESSFULLY);
        updateResponse.setId(userId);
        return updateResponse;
    }

}
