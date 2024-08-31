package app.com.icare.controllers.user;


import app.com.icare.models.User;
import app.com.icare.services.roles.RoleService;
import app.com.icare.services.users.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
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


        return userService.save(user);
    }


    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId, @RequestBody User userDetails) {
        Optional<User> user = userService.findByUserId(userId);
        if (user.isPresent()) {
          return  this.userService.updateUser(userDetails);
        }else{
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

