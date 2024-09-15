package teya.exercise.bank.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name="id", unique = true, nullable = false)
    public UUID id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="address")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created")
    @CreationTimestamp
    public Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified")
    @UpdateTimestamp
    public Date modified;

    @Column(name="isDeleted")
    public boolean isDeleted;
}
