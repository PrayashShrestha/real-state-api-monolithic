package miu.ea.realestateapimonolithic.controller;

import lombok.RequiredArgsConstructor;
import miu.ea.realestateapimonolithic.common.Constant;
import miu.ea.realestateapimonolithic.model.Role;
import miu.ea.realestateapimonolithic.service.impl.RoleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.ROLE_URL_PREFIX)
public class RoleController {
    private final RoleServiceImpl roleService;

    @PostMapping
    public Role saveRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @GetMapping
    public List<Role> findAllRoles(){
        return roleService.findAllRoles();
    }

    @GetMapping("/{id}")
    public Role findRole(@PathVariable long id){
        return roleService.findRole(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable long id,@RequestBody Role role){
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable long id){
        roleService.deleteRole(id);
    }
}
