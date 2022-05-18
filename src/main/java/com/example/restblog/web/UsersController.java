package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.restblog.data.User.Role.ADMIN;
import static com.example.restblog.data.User.Role.USER;


@CrossOrigin
@RestController
@RequestMapping(value="/api/users", headers = "Accept=application/json")
public class UsersController {

    List<User> userList = new ArrayList<>();

    @GetMapping
    public List<User> getAll(){
        userList.add(new User(1L, "User1", "user1@email.org", "securepassword123"));
        userList.add(new User(2L, "User2", "user2@email.org", "passwordsareforlosers"));

        return userList;
    }

    @GetMapping("/{id}")
    public User getById(@RequestParam("id") Long id) {
        for (User user : getAll()) {
            if(Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return new User();
    }

    @PostMapping
    public void createUser(@RequestBody User newUser) {
        System.out.println(newUser);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        System.out.println(updatedUser);
        System.out.println(id);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable Long id) {
        System.out.println("Deleting user with ID: " + id);
    }
}
