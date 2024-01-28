package com.example.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_id_seq",sequenceName = "users_id_seq", allocationSize = 1)
    private long id;
    private String uuid;
    private String login;
    private String email;
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "islock")
    private boolean isLock;
    @Column(name = "isenabled")
    private boolean isEnabled;

    public User(long id, String uuid, String login, String email, String password, Role role, boolean isLock, boolean isEnabled) {
        this.id = id;
        this.uuid = uuid;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLock = isLock;
        this.isEnabled = isEnabled;
        generateUuid();
    }

    public User() {
        generateUuid();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void generateUuid(){
        if(uuid == null || uuid.isEmpty()) {
            setUuid(UUID.randomUUID().toString());
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
