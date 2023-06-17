package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.dtos.request.LoanApplicationRequest;
import africa.semicolon.loanAppSystem.dtos.request.RepaymentPreferenceRequest;
import africa.semicolon.loanAppSystem.dtos.response.RepaymentResponse;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserStillHasARunningLoanException;

public interface RepaymentService {
    public RepaymentResponse updateRepaymentReference(RepaymentPreferenceRequest repaymentPreferenceRequest) throws UserNotFoundException, UserStillHasARunningLoanException;
}