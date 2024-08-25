package app.com.icare.controllers.user;


import app.com.icare.models.Role;
import app.com.icare.models.User;
import app.com.icare.services.roles.RoleService;
import app.com.icare.services.users.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) {
        Optional<User> user = userService.findByUserId(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {

        if (user.getRole() != null) {
            Optional<Role> role = roleService.findRoleById(user.getRole().getId());
            if (role.isEmpty()) {
                Role newRole = new Role();
                newRole.setName(user.getRole().getName());
                roleService.saveRole(newRole);
                user.setRole(newRole);
            } else {
                user.setRole(role.get());
            }
        }

        return userService.save(user);
    }


    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId, @RequestBody User userDetails) {
        Optional<User> user = userService.findByUserId(userId);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setUsername(userDetails.getUsername());
            updatedUser.setPasswordHash(userDetails.getPasswordHash());
            updatedUser.setLastLogin(userDetails.getLastLogin());
            // Fetch the Role from the database to avoid the detached entity issue
            if (userDetails.getRole() != null) {
                Optional<Role> role = roleService.findRoleById(userDetails.getRole().getId());
                if (role.isEmpty()) {
                    Role newRole = new Role();
                    newRole.setName(userDetails.getRole().getName());
                    roleService.saveRole(newRole);
                    updatedUser.setRole(newRole);
                } else {
                    updatedUser.setRole(role.get());
                }
            }
            return ResponseEntity.ok(userService.save(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") int userId) {
        Optional<User> user = userService.findByUserId(userId);
        if (user.isPresent()) {
            userService.delete(user.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

