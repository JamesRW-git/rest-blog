package com.example.restblog.web;

import com.example.restblog.data.Post;
//import com.example.restblog.service.EmailService;
import com.example.restblog.service.PostService;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@CrossOrigin
@RestController
@RequestMapping(value="/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final UserService userService;
    private final PostService postService;
//    private final EmailService emailService;

    public PostsController(UserService userService, PostService postService /*EmailService emailService*/) {
        this.userService = userService;
        this.postService = postService;
//        this.emailService = emailService;
    }


    @GetMapping
    public List<Post> getAll(){
        return postService.getPostList();
    }

    @GetMapping("/{id}")
    public Post getById(@RequestParam("id") Long id) {
        for (Post post : postService.getPostList()) {
            if(Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return null;
    }

    @PostMapping
    public void createPost(@RequestBody Post newPost) {
        System.out.println(newPost);
    }

    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody Post newPost ) {
        postService.addPost(newPost, username);
//        emailService.prepareAndSend(newPost, "New Post: Blah" , "Blah");
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        postService.updatePost(id, updatedPost);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
