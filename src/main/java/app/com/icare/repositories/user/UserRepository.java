package app.com.icare.repositories.user;

import app.com.icare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByusername(String username);
}
