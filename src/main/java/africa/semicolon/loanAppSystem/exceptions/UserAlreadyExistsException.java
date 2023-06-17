package africa.semicolon.loanAppSystem.exceptions;

public class UserAlreadyExistsException extends LoanAppException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
