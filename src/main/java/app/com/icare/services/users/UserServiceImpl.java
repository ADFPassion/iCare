package app.com.icare.services.users;

import app.com.icare.models.Role;
import app.com.icare.models.User;
import app.com.icare.records.UserRecord;
import app.com.icare.repositories.role.RoleRepository;
import app.com.icare.repositories.user.UserRepository;
import app.com.icare.services.roles.RoleService;
import app.com.icare.services.roles.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }


    @Override
    public User save(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        if (user.getRole() != null) {
            Role role = user.getRole();
            Role savedRole = saveOrFetchRole(role);
            user.setRole(savedRole);
        }

        return userRepository.save(user);
    }


    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public ResponseEntity<User> updateUser(User _user) {
        // Fetch the existing user by username
        User user = this.userRepository.findByusername(_user.getUsername());

        // Update username and password
        user.setPasswordHash(passwordEncoder.encode(_user.getPasswordHash())); // Use _user's passwordHash
        user.setUsername(_user.getUsername());

        // Check if a role is provided in the request body
        if (_user.getRole() != null) {
            // Fetch the role from the provided role ID in the request body
            Optional<Role> role = this.roleRepository.findById(_user.getRole().getId());

            // If role exists, set it to the user, otherwise save the new role
            if (role.isPresent()) {
                user.setRole(role.get());
            } else {
                Role saved = roleRepository.save(_user.getRole());
                user.setRole(saved);
            }
        }

        // Save the updated user and return response
        return ResponseEntity.ok(userRepository.save(user));
    }


    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByusername(username);
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserId(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<UserRecord> getUserRecord(int userId) {
        // Fetch the user entity from the repository
        return userRepository.findById(userId)
                // Map the User entity to a UserRecord
                .map(user -> new UserRecord(user.getUser_id(), user.getUsername(), user.getRole()));
    }

    private Role saveOrFetchRole(Role role) {
        // If the role ID is null, it's a new role, so save it
        if (role.getId() == null) {
            return roleRepository.save(role);
        }
        // If the role already exists, fetch it from the database
        return roleRepository.findById(role.getId()).orElseGet(() -> roleRepository.save(role));
    }
}
