package miu.ea.realestateapimonolithic.repository;

import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(RoleEnum roleEnum);
}