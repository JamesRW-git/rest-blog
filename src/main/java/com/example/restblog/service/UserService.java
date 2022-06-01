package com.example.restblog.service;

import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getUsersList() { //TODO: rename this getAllUsers()
        return usersRepository.findAll();
    }

    // from UsersController
    public User getUserById(Long id) {
        //TODO: user usersRepository.findById(id).orElseThrow()
        return usersRepository.findById(id).orElseThrow(); //throws an exception if the user cannot be found by id
    }

    // Taken from UsersController
    public User getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public void updateEmail(Long userId, String newEmail) {
        User user = getUserById(userId);
        user.setEmail(newEmail);
        usersRepository.save(user);
    }

    public void createUser(User user) {
        usersRepository.save(user);
    }

    public User getByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow();
    }
}
