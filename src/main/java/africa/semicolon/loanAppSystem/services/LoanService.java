package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.dtos.request.LoanApplicationRequest;
import africa.semicolon.loanAppSystem.dtos.response.LoanApplicationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UpdateResponse;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserStillHasARunningLoanException;

public interface LoanService {
    LoanApplicationResponse applyForLoan(LoanApplicationRequest loanApplicationRequest) throws UserNotFoundException, UserStillHasARunningLoanException;
    UpdateResponse loanStatusUpdate(String name, LoanApplicationRequest loanApplicationRequest) throws UserNotFoundException, UserStillHasARunningLoanException;

    void deleteAll();
}
