package miu.ea.realestateapimonolithic.service.impl;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.RoleEnum;
import miu.ea.realestateapimonolithic.exception.AlreadyExistException;
import miu.ea.realestateapimonolithic.exception.NotFoundException;
import miu.ea.realestateapimonolithic.model.Role;
import miu.ea.realestateapimonolithic.repository.RoleRepository;
import miu.ea.realestateapimonolithic.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public Role saveRole(Role role){
        RoleEnum roleEnum = role.getRole();
        Optional<Role> retrievedRole = roleRepository.findRoleByRole(roleEnum);
        if(retrievedRole.isPresent()){
            throw new AlreadyExistException(String.format("%s already exists.", roleEnum));
        }
        return roleRepository.save(role);
    }

    public Role findRole(long id){
        Optional<Role> role = roleRepository.findById(id);
        if(role.isEmpty()){
            throw new NotFoundException("Role not found.");
        }
        return role.get();
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public Role updateRole(long id, Role role){
        Optional<Role> retrievedRole = roleRepository.findById(id);
        if(retrievedRole.isEmpty()){
            throw new NotFoundException("Role not Found.");
        }

        Role updatedRole = retrievedRole.get();

        updatedRole.setRole(role.getRole());

        roleRepository.save(updatedRole);
        return updatedRole;
    }

    public void deleteRole(long id){
        roleRepository.deleteById(id);
    }
}
