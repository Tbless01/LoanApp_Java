package africa.semicolon.loanAppSystem.controllers;

import africa.semicolon.loanAppSystem.dtos.request.LoanApplicationRequest;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.LoanApplicationResponse;
import africa.semicolon.loanAppSystem.dtos.response.UpdateResponse;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.exceptions.UserAlreadyExistsException;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;
import africa.semicolon.loanAppSystem.exceptions.UserStillHasARunningLoanException;
import africa.semicolon.loanAppSystem.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class LoanApplicationController {
    private final LoanService loanService;

    @PostMapping("/user/apply")
    public ResponseEntity<?> apply(@RequestBody LoanApplicationRequest request) {
        try {
            LoanApplicationResponse response = loanService.applyForLoan(request);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | UserStillHasARunningLoanException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateLoanStatus(@PathVariable String name, @RequestBody LoanApplicationRequest request) {
        try {
            UpdateResponse update = loanService.loanStatusUpdate(name, request);
            return ResponseEntity.ok(update);
        } catch (UserNotFoundException | UserStillHasARunningLoanException e) {
            throw new RuntimeException(e);
        }
    }
}

