package app.com.icare.services.users;

import app.com.icare.models.User;
import app.com.icare.records.UserRecord;
import app.com.icare.repositories.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public User save(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return null;
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


}
