package africa.semicolon.loanAppSystem.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repayment_preferences")
public class RepaymentPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String loanAccountName;
    private String bankName;
    private LocalDateTime paymentDate;
    private BigDecimal paymentAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private float interestRate;

    @UuidGenerator
    private UUID referenceNumber;
}

