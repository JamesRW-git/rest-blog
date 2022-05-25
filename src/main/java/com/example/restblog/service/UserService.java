package com.example.restblog.service;

import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;
    private final CategoriesRepository categoriesRepository;

    public UserService(UsersRepository usersRepository, PostsRepository postsRepository, CategoriesRepository categoriesRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public List<User> getUsersList() { //TODO: rename this getAllUsers()
        return usersRepository.findAll();
    }

    public List<Post> getPostList() {
        return postsRepository.findAll();
    }

    public void addPost(Post newPost, String username) {
        // get the User object who made the post
        User user = getUserByUsername(username);

        // associate the post with the user object
        user.getPosts().add(newPost);

        // associate the user with the post object
        newPost.setUser(user);

        List<Category> categoriesToAdd = new ArrayList<>();

        for (Category category : newPost.getCategories()){
            categoriesToAdd.add(categoriesRepository.findCategoryByName(category.getName()));
        }

        postsRepository.save(newPost);
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

    public void deletePostById(Long id) {
        postsRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

//    private List<User> setUserList() {
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(1L, "User1", "user1@email.org", "securepassword123"));
//        userList.add(new User(2L, "User2", "user2@email.org", "passwordsareforlosers"));
//
//        return userList;
//    }
//
//    public List<Post> setPostList(){
//        List<Post> postList = new ArrayList<>();
//        postList.add(new Post(1L,"Cheese is great","Most people love cheese", userList.get(0)));
//        postList.add(new Post(2L,"What is sleep?", "Sleep is the cousin of death", userList.get(1)));
//        postList.add(new Post(3L,"Javelin vs. T-72BV", "Javelin wins", userList.get(0)));
//
//        return postList;
//    }

}
