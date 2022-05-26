package com.example.restblog.service;

import com.example.restblog.data.*;
import com.example.restblog.dto.CreatePostDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostsRepository postsRepository;
    private final CategoriesRepository categoriesRepository;
    private final UserService userService;

    public PostService(PostsRepository postsRepository, CategoriesRepository categoriesRepository, UserService userService) {
        this.postsRepository = postsRepository;
        this.categoriesRepository = categoriesRepository;
        this.userService = userService;
    }

    public List<Post> getPostList() {
        return postsRepository.findAll();
    }

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTitleLike(keyword);
    }

    public void addPost(CreatePostDto dto, Post newPost, String username) {
        User user = userService.getUserByUsername(username);

        newPost.setTitle(dto.getTitle());
        newPost.setContent(dto.getContent());

        user.getPosts().add(newPost);

        newPost.setUser(user);

        List<Category> categoriesToAdd = new ArrayList<>();

        for (String categoryName : dto.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(categoryName));
        }

        newPost.setCategories(categoriesToAdd);

        postsRepository.save(newPost);
    }
}
