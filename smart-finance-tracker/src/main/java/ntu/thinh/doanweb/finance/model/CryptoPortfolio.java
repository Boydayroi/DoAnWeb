package ntu.thinh.doanweb.finance.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "crypto_portfolios")
@Data
public class CryptoPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "coin_id", nullable = false)
    private String coinId;

    @Column(nullable = false, precision = 15, scale = 6)
    private BigDecimal quantity;
}
