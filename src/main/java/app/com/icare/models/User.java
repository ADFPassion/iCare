package app.com.icare.models;

import app.com.icare.validators.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sequence_Generator")
    @SequenceGenerator(name = "Sequence_Generator", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "user_id")
    private int user_id;

    private String username;

    @Column(name = "password_hash", nullable = false, length = Integer.MAX_VALUE)
    @NotBlank
    @ValidPassword
    private String passwordHash;

    @Column(name = "last_login")
    private Instant lastLogin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User() {
    }

    public User(int user_id, String username) {
        this.user_id = user_id;
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username);
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", username='" + username + '\'' + ", passwordHash='" + passwordHash + '\'' + ", lastLogin=" + lastLogin + ", role=" + role + '}';
    }
}
