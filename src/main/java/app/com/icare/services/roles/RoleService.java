package app.com.icare.services.roles;

import app.com.icare.models.Role;

import java.util.Optional;

public interface RoleService {


    void saveRole(Role role);
    void deleteRole(Role role);
    Role getRole(String roleName);
    Optional<Role> findRoleById(int id);
}
