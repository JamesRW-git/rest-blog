package com.example.restblog.service;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> userList = setUserList();
    private List<Post> posts = setPostList();

    public List<User> getUsersList() {
        return userList;
    }

    public List<Post> getPostList() {
        return posts;
    }

    public void addPost(Post newPost, String username) {
        // get the User object who made the post
        User user = getUserByUsername(username);

        // associate the post with the user object
        user.getPosts().add(newPost);

        // associate the user with the post object
        newPost.setUser(user);

        // add the post to the post list (our pretend database)
        posts.add(newPost);
    }

    // from UsersController
    public User getUserById(Long id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    // Taken from UsersController
    public User getUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    private List<User> setUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "User1", "user1@email.org", "securepassword123"));
        userList.add(new User(2L, "User2", "user2@email.org", "passwordsareforlosers"));

        return userList;
    }

    public List<Post> setPostList(){
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(1L,"Cheese is great","Most people love cheese"));
        postList.add(new Post(2L,"What is sleep?", "Sleep is the cousin of death"));
        postList.add(new Post(3L,"Javelin vs. T-72BV", "Javelin wins"));

        return postList;
    }

}
