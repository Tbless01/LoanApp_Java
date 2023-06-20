package africa.semicolon.loanAppSystem.utils;

import africa.semicolon.loanAppSystem.data.models.*;
import africa.semicolon.loanAppSystem.dtos.request.LoanApplicationRequest;
import africa.semicolon.loanAppSystem.dtos.request.RepaymentPreferenceRequest;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

public class Mapper {

    public static void map(UserRegistrationRequest userRegistrationRequest, User user){
        user.setUsername(userRegistrationRequest.getUsername());
//        user.setPassword(userRegistrationRequest.getPassword());
        user.setRole(userRegistrationRequest.getRole());
    }
    public static void map(LoanApplicationRequest loanApplicationRequest, LoanApplication loanApplication){
        loanApplication.setCustomerName(loanApplicationRequest.getCustomerName());
        loanApplication.setLoanAmount(loanApplicationRequest.getLoanAmount());
        loanApplication.setPurpose(loanApplicationRequest.getPurpose());
        loanApplication.setStatus(LoanApplicationStatus.valueOf(LoanApplicationStatus.IN_PROGRESS.getStatus()));
        loanApplication.setDateApplied(LocalDateTime.now());
    }
    public static void map(RepaymentPreferenceRequest repaymentPreferenceRequest, RepaymentPreference repaymentPreference){
        repaymentPreference.setBankName(repaymentPreferenceRequest.getBankName());
        repaymentPreference.setLoanAccountName(repaymentPreferenceRequest.getLoanAccountName());
        repaymentPreference.setPaymentAmount(repaymentPreferenceRequest.getPaymentAmount());
        repaymentPreference.setPaymentMethod(PaymentMethod.valueOf(repaymentPreferenceRequest.getPaymentMethod().name()));
        repaymentPreference.setUsername(repaymentPreferenceRequest.getUsername());
        repaymentPreference.setInterestRate((float) 0.15);
        repaymentPreference.setReferenceNumber(repaymentPreferenceRequest.getReferenceNumber());
        repaymentPreference.setPaymentDate(LocalDateTime.now());
    }
    public static void map(LoanApplicationRequest loanApplicationRequest, User user) {
        user.getLoanApplication().setCustomerName(loanApplicationRequest.getCustomerName());
        user.getLoanApplication().setLoanAmount(loanApplicationRequest.getLoanAmount());
        user.getLoanApplication().setPurpose(loanApplicationRequest.getPurpose());
        user.getLoanApplication().setDateApplied(LocalDateTime.now());
        user.getLoanApplication().setStatus(LoanApplicationStatus.valueOf(LoanApplicationStatus.IN_PROGRESS.getStatus()));
    }

}
