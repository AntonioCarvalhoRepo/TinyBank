package teya.exercise.bank.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query(value = "SELECT * from accounts WHERE user_id= ?1",nativeQuery=true)
    List<Account> findAccountsByUser(UUID userId);

    @Modifying
    @Query("UPDATE Account account set account.balance = ?1 where account.id = ?2")
    void updateBalance(double balance, UUID accountId);
}
