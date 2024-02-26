package com.team1.epicenergyservices.entities;

import com.team1.epicenergyservices.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.usertype.UserType;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {
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
    private TypeUser userType;

    public User(String username, String email, String password, String name, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.avatar = "https://ui-avatars.com/api/?name" + name + "+" + lastName;
        this.userType = TypeUser.USER;
    }
}
