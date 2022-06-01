package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value="/api/users", headers = "Accept=application/json")
public class UsersController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    //Inject userService
    public UsersController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("create")
    public void create(@RequestBody User newUser){
        // TODO: inject the PasswordEncoder and set the incoming password as below
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.createUser(newUser);
    }

    @GetMapping("me")
    public User getCurrentUser(OAuth2Authentication auth){
        return userService.getByEmail(auth.getName());
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getUsersList();
    }

    @GetMapping("{id}") //TODO: REMEMBER, DO NOT PUT CURLY BRACES AROUND REQUESTPARAM, ONLY PATHVARIABLE
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam("username") String username) {
        System.out.println("Getting user with username: " + username);
        return userService.getUserByUsername(username);
    }

    @GetMapping("email")
    public User getByEmail(@RequestParam("email") String email) {
        System.out.println("Getting user with email: " + email);
        return null;
    }

    @PostMapping
    public void createUser(@RequestBody User newUser) {
        userService.getUsersList().add(newUser);
    }

    @PostMapping("{username}")
    public void addUserPost(@PathVariable String username, @RequestBody Post newPost) {
        User user = userService.getUserByUsername(username);
        user.getPosts().add(newPost);
    }

//    either the userid == current user or the current user is an admin
    @PreAuthorize("hasAuthority('ADMIN') || @userService.getUserById(#id).email == authentication.name")
    @PutMapping("{id}/updatePassword")
    public void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword ) {
        User userToUpdate = getById(id);
        userToUpdate.setPassword(newPassword);
        System.out.println(userToUpdate.getPassword());
    }
}
