package app.com.icare.services.users;

import app.com.icare.models.User;
import app.com.icare.records.UserRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {



    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByUserId(int userId);

    Optional<UserRecord> getUserRecord(int userId);

    User save(User user);

    void delete(User user);
    ResponseEntity<User> updateUser(User user);
}
