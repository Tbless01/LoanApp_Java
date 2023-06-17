package africa.semicolon.loanAppSystem.dtos.request;

import africa.semicolon.loanAppSystem.data.models.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class RepaymentPreferenceRequest {
    private Long id;
    private String username;
    private String loanAccountName;
    private String bankName;
    private LocalDateTime paymentDate;
    private BigDecimal paymentAmount;
    private PaymentMethod paymentMethod;
    private float interestRate;
    private UUID referenceNumber;
}
