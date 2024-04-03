package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
