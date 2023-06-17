package africa.semicolon.loanAppSystem.dtos.request;


import africa.semicolon.loanAppSystem.data.models.LoanApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class LoanApplicationRequest {
    private Long id;
    private String customerName;
    private double loanAmount;
    private String purpose;

    private LoanApplicationStatus status;
    private LocalDateTime dateApplied;
}