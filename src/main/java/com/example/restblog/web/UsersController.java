package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.restblog.data.User.Role.ADMIN;
import static com.example.restblog.data.User.Role.USER;


@CrossOrigin
@RestController
@RequestMapping(value="/api/users", headers = "Accept=application/json")
public class UsersController {

    List<User> userList = new ArrayList<>();

    @GetMapping
    public List<User> getAll(){
        userList.add(new User(1L, "User1", "user1@email.org", "securepassword123", new Date(100, Calendar.APRIL, 5), USER));
        userList.add(new User(2L, "User2", "user2@email.org", "passwordsareforlosers", new Date(76, Calendar.FEBRUARY, 14), ADMIN));

        return userList;
    }
}
