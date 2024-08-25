package app.com.icare;

import app.com.icare.models.Role;
import app.com.icare.models.User;
import app.com.icare.records.UserRecord;
import app.com.icare.services.roles.RoleService;
import app.com.icare.services.users.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ICareApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ICareApplicationTests.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Test
    void contextLoads() {
//        Role role = new Role();
//        role.setName("Bishop");
//
//        User user = new User();
//        user.setUsername("Anish");
//        user.setPasswordHash("anish@007");
//        user.setRole(role);
//
//        userService.save(user);


    }


}
