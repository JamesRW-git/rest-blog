package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username); //translates to: select * from users u where u.username = "username"
}
