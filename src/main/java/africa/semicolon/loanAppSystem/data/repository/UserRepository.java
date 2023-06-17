package africa.semicolon.loanAppSystem.data.repository;

import africa.semicolon.loanAppSystem.data.models.User;
import africa.semicolon.loanAppSystem.data.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findUsersByRole(UserRole role);
    User findAUsersByRole(UserRole role);
}
