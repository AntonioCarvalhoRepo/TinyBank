package teya.exercise.bank.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Modifying
    @Query("UPDATE User user set user.isDeleted = true where user.id = ?1")
    void disable(UUID name);

    @Query("SELECT id,name,address,age,created,modified FROM User user WHERE user.isDeleted=false")
    List<User> findAllUsers();
}
