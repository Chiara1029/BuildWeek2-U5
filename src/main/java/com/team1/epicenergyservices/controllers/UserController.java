package com.team1.epicenergyservices.controllers;

import com.team1.epicenergyservices.entities.User;
import com.team1.epicenergyservices.exceptions.BadRequestException;
import com.team1.epicenergyservices.payloads.UserDTO;
import com.team1.epicenergyservices.repositories.UserRepository;
import com.team1.epicenergyservices.services.AuthService;
import com.team1.epicenergyservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userSrv;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authSrv;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated UserDTO user, BindingResult validation) throws Exception{
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return authSrv.save(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id){
        return userSrv.findById(id);
    }

    @PutMapping("/{id}")
    public User findAndUpdate(@PathVariable UUID id, @RequestBody User user){
        return userSrv.findByIdAndUpdate(id, user);
    }

    @PatchMapping("/{id}/avatar")
    public User uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID id) {
        try {
            return userSrv.uploadAvatar(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID id){
        userSrv.findByIdAndDelete(id);
    }
}
