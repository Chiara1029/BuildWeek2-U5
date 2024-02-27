package com.team1.epicenergyservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.epicenergyservices.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.usertype.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String avatar;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private TypeUser userType;

    public User(String username, String email, String password, String name, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.userType.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}