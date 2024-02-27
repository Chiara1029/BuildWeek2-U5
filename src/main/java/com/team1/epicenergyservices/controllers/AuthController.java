package com.team1.epicenergyservices.controllers;


import com.team1.epicenergyservices.entities.User;
import com.team1.epicenergyservices.payloads.UserDTO;
import com.team1.epicenergyservices.payloads.login.UserLoginDTO;
import com.team1.epicenergyservices.payloads.login.UserLoginResponseDTO;
import com.team1.epicenergyservices.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authSrv;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO userLog){
        String accessToken = authSrv.authenticateUserAndGenerateToken(userLog);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserDTO newUser) throws IOException {
        return this.authSrv.save(newUser);
    }
}
