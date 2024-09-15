package teya.exercise.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import teya.exercise.bank.type.TransactionType;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", unique = true, nullable = false)
    public UUID id;

    @Column(name="fromAccount")
    private UUID from;

    @Column(name="toAccount")
    private UUID to;

    @Column(name="amount")
    private double amount;

    @Column(name="type")
    private TransactionType type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created")
    @CreationTimestamp
    public Date created;
}

