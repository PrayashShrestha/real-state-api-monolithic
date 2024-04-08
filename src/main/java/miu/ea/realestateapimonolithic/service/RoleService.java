package miu.ea.realestateapimonolithic.service;

import miu.ea.realestateapimonolithic.model.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    Role findRole(long id);
    List<Role> findAllRoles();
    Role updateRole(long id, Role role);
    void deleteRole(long id);
}
