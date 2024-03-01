package com.team1.epicenergyservices.services;

import com.team1.epicenergyservices.configuration.MailgunConfig;
import com.team1.epicenergyservices.entities.User;
import com.team1.epicenergyservices.enums.TypeUser;
import com.team1.epicenergyservices.exceptions.BadRequestException;
import com.team1.epicenergyservices.exceptions.UnauthorizedException;
import com.team1.epicenergyservices.payloads.UserDTO;
import com.team1.epicenergyservices.payloads.login.UserLoginDTO;
import com.team1.epicenergyservices.repositories.UserRepository;
import com.team1.epicenergyservices.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UserService userSrv;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private MailgunConfig mailgun;

    public String authenticateUserAndGenerateToken(UserLoginDTO userLog) {
        User user = userSrv.findByEmail(userLog.email());
        if (bcrypt.matches(userLog.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Invalid credentials, try again.");
        }
    }

    public User save(UserDTO user) throws IOException {
        userRepository.findByEmail(user.email()).ifPresent(us -> {
            throw new BadRequestException("Email: " + user.email() + " already exist.");
        });
        User newUser = new User();
        newUser.setUsername(user.username());
        newUser.setEmail(user.email());
        newUser.setPassword(bcrypt.encode(user.password()));
        newUser.setName(user.name());
        newUser.setLastName(user.lastName());
        newUser.setAvatar("https://ui-avatars.com/api/?name" + user.name() + user.lastName());
        newUser.setUserType(TypeUser.USER);

        User savedUser = userRepository.save(newUser);
        mailgun.sendRegistrationEmail(newUser);
        return savedUser;
    }
}
