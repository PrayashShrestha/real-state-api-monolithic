package miu.ea.realstateapimonolithic.repository;

import miu.ea.realstateapimonolithic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
