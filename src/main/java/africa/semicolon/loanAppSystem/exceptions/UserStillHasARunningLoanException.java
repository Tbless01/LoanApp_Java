package africa.semicolon.loanAppSystem.exceptions;

public class UserStillHasARunningLoanException extends LoanAppException{
    public UserStillHasARunningLoanException(String message) {
        super(message);
    }
}
