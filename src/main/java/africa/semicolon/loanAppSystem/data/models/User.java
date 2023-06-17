package africa.semicolon.loanAppSystem.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private RepaymentPreference repaymentPreference;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoanApplication loanApplication;

//    @Column(nullable = false)
//    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public void applyForLoan(){
        LoanApplication newLoan = new LoanApplication();
        this.loanApplication = newLoan;
    }
}


