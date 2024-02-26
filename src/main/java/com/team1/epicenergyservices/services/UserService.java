package com.team1.epicenergyservices.services;

import com.team1.epicenergyservices.entities.User;
import com.team1.epicenergyservices.exceptions.BadRequestException;
import com.team1.epicenergyservices.exceptions.NotFoundException;
import com.team1.epicenergyservices.payloads.UserDTO;
import com.team1.epicenergyservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " not found."));
    }

    public User save(UserDTO user) throws IOException{
        userRepository.findByEmail(user.email()).ifPresent( us -> {
            throw new BadRequestException("Email: " + user.email() + " already exist.");
        });
        User newUser = new User();
        newUser.setUsername(user.username());
        newUser.setEmail(user.email());
        newUser.setPassword(user.password());
        newUser.setName(user.name());
        newUser.setLastName(user.lastName());

        return userRepository.save(newUser);
    }

    public User findById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIdAndUpdate(UUID id, User user){
        User userFound = this.findById(id);
        userFound.setUsername(user.getUsername());
        userFound.setEmail(user.getEmail());
        userFound.setPassword(user.getPassword());
        userFound.setName(user.getName());
        userFound.setLastName(user.getLastName());

        return userRepository.save(userFound);
    }

    public void findByIdAndDelete(UUID userId) {
        User userFound = this.findById(userId);
        userRepository.delete(userFound);
    }

}
