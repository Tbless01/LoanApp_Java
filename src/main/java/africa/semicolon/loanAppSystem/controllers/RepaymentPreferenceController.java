package africa.semicolon.loanAppSystem.controllers;

import africa.semicolon.loanAppSystem.dtos.request.LoanApplicationRequest;
import africa.semicolon.loanAppSystem.dtos.request.RepaymentPreferenceRequest;
import africa.semicolon.loanAppSystem.dtos.response.LoanApplicationResponse;
import africa.semicolon.loanAppSystem.dtos.response.RepaymentResponse;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserStillHasARunningLoanException;
import africa.semicolon.loanAppSystem.services.LoanService;
import africa.semicolon.loanAppSystem.services.RepaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RepaymentPreferenceController {
    private final RepaymentService repaymentService;

    @PostMapping("/loan/repayment")
    public ResponseEntity<?> apply(@RequestBody RepaymentPreferenceRequest request) {
        try {
            RepaymentResponse response = repaymentService.updateRepaymentReference(request);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | UserStillHasARunningLoanException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
