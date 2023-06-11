package africa.semicolon.loanAppSystem.data.repository;

import africa.semicolon.loanAppSystem.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
