package africa.semicolon.loanAppSystem.exceptions;

public class UserNotFoundException extends LoanAppException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
