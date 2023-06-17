package africa.semicolon.loanAppSystem.data.repository;

import africa.semicolon.loanAppSystem.data.models.LoanApplication;
import africa.semicolon.loanAppSystem.data.models.LoanApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByStatus(LoanApplicationStatus status);
    LoanApplication findByCustomerName(String username);
}
