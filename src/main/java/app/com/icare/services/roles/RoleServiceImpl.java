package app.com.icare.services.roles;

import app.com.icare.models.Role;
import app.com.icare.repositories.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;




    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        this.roleRepository.delete(role);
    }

    @Override
    public Role getRole(String roleName) {
        return this.roleRepository.findByName(roleName);
    }

    @Override
    public Optional<Role> findRoleById(int id) {
        return this.roleRepository.findById(id);
    }
}
