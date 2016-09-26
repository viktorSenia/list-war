package com.leo.test.list.war.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    @Length(min = 1, max = 20, message = "error.username")
    private String username;

    @Column(nullable = false)
    @Length(min = 1, max = 64, message = "error.password")
    private String password;

    @Transient
    private String confirmPassword;

    @Transient
    @AssertTrue(message = "error.registrationTerms")
    private boolean registrationTerms;

    @Column(unique = true, nullable = false, length = 64)
    @NotBlank(message = "error.email")
    @Email(message = "error.email")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "error.role")
    private Role role = Role.ROLE_USER;

    private boolean enabled = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isRegistrationTerms() {
        return registrationTerms;
    }

    public void setRegistrationTerms(boolean registrationTerms) {
        this.registrationTerms = registrationTerms;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(role.name()));
        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
