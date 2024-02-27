package com.team1.epicenergyservices.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.team1.epicenergyservices.entities.User;
import com.team1.epicenergyservices.enums.TypeUser;
import com.team1.epicenergyservices.exceptions.BadRequestException;
import com.team1.epicenergyservices.exceptions.NotFoundException;
import com.team1.epicenergyservices.payloads.UserDTO;
import com.team1.epicenergyservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinaryUploader;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " not found."));
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

    public User uploadAvatar(UUID id, MultipartFile file) throws IOException{
        User userFound = this.findById(id);
        String avatarURL = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        userFound.setAvatar(avatarURL);
        return userRepository.save(userFound);
    }
}
