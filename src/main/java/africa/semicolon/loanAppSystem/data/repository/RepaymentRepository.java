package africa.semicolon.loanAppSystem.data.repository;

import africa.semicolon.loanAppSystem.data.models.RepaymentPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepaymentRepository extends JpaRepository<RepaymentPreference, Long> {
}
