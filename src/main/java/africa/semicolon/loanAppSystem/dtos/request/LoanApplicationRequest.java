package africa.semicolon.loanAppSystem.dtos.request;


import africa.semicolon.loanAppSystem.data.models.LoanApplicationStatus;

public class LoanApplicationRequest {
    private Long id;

    private String customerName;
    private double loanAmount;
    private String purpose;
    private LoanApplicationStatus status;
}