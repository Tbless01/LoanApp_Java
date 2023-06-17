package africa.semicolon.loanAppSystem.services;

import africa.semicolon.loanAppSystem.data.models.LoanApplicationStatus;
import africa.semicolon.loanAppSystem.data.models.PaymentMethod;
import africa.semicolon.loanAppSystem.data.models.RepaymentPreference;
import africa.semicolon.loanAppSystem.data.repository.LoanApplicationRepository;
import africa.semicolon.loanAppSystem.data.repository.RepaymentRepository;
import africa.semicolon.loanAppSystem.dtos.request.RepaymentPreferenceRequest;
import africa.semicolon.loanAppSystem.dtos.response.RepaymentResponse;
import africa.semicolon.loanAppSystem.exceptions.UserNotFoundException;
import africa.semicolon.loanAppSystem.exceptions.UserStillHasARunningLoanException;
import africa.semicolon.loanAppSystem.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static africa.semicolon.loanAppSystem.utils.ExceptionUtils.KINDLY_WAIT_PATIENTLY_TO_GET_YOUR_LOAN_APPROVED;
import static africa.semicolon.loanAppSystem.utils.ExceptionUtils.REPAYMENT_REFERENCE_SUCCESSFULLY_UPDATED;

@Service
@AllArgsConstructor
public class RepaymentServicesImplementation implements RepaymentService {
    private final LoanApplicationRepository loanApplicationRepository;
    private final RepaymentRepository repaymentRepository;

    @Override
    public RepaymentResponse updateRepaymentReference(RepaymentPreferenceRequest repaymentPreferenceRequest) throws UserNotFoundException, UserStillHasARunningLoanException {
        if (repaymentPreferenceRequest.getPaymentMethod().getMethod().equalsIgnoreCase(PaymentMethod.CARD.getMethod()) || repaymentPreferenceRequest.getPaymentMethod().getMethod().equalsIgnoreCase(PaymentMethod.USSD_CODE.getMethod()) || repaymentPreferenceRequest.getPaymentMethod().getMethod().equalsIgnoreCase(PaymentMethod.BANK_TRANSFER.getMethod()))
            if (!checkIfLoanWasApproved(repaymentPreferenceRequest.getUsername()))
                throw new UserStillHasARunningLoanException(KINDLY_WAIT_PATIENTLY_TO_GET_YOUR_LOAN_APPROVED);
        RepaymentPreference repaymentPreference = new RepaymentPreference();
        Mapper.map(repaymentPreferenceRequest, repaymentPreference);
        RepaymentPreference repaymentPreferenceSaved = repaymentRepository.save(repaymentPreference);
        return repaymentResponse(repaymentPreferenceSaved.getId());
    }

    private boolean checkIfLoanWasApproved(String username) {
        var foundUser = loanApplicationRepository.findByCustomerName(username);
        if (foundUser != null)
            if (foundUser.getStatus().name().equalsIgnoreCase(LoanApplicationStatus.APPROVED.getStatus()))
                return true;
        return false;
    }

    private static RepaymentResponse repaymentResponse(Long userId) {
        RepaymentResponse repaymentResponse = new RepaymentResponse();
        repaymentResponse.setMessage(REPAYMENT_REFERENCE_SUCCESSFULLY_UPDATED);
        repaymentResponse.setId(userId);
        return repaymentResponse;
    }
}
