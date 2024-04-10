package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    public void updateFailedAttempt(int failAttempts, String email);
}
