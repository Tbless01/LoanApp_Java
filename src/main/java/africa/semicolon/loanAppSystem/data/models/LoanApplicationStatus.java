package africa.semicolon.loanAppSystem.data.models;

public enum LoanApplicationStatus {
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    IN_PROGRESS("IN_PROGRESS"),
    CLOSED("CLOSED");

    private final String status;

    LoanApplicationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
