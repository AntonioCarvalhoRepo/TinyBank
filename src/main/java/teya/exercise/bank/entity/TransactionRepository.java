package teya.exercise.bank.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query(value = "SELECT * from transactions WHERE from_account= ?1",nativeQuery=true)
    List<Transaction> getTransactionByAccountId(UUID accountId);
}
