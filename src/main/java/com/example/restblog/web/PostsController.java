package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@CrossOrigin
@RestController
@RequestMapping(value="/api/posts", headers = "Accept=application/json")
public class PostsController {

    List<Post> postList = new ArrayList<>();

    @GetMapping
    public List<Post> getAll(){
        postList.add(new Post(1L,"Cheese is great","Most people love cheese"));
        postList.add(new Post(2L,"What is sleep?", "Sleep is the cousin of death"));
        postList.add(new Post(3L,"Javelin vs. T-72BV", "Javelin wins"));

        return postList;
    }

    @GetMapping("/{id}")
    public Post getById(@RequestParam("id") Long id) {
        for (Post post : getAll()) {
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

    @PutMapping("{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        System.out.println(updatedPost);
        System.out.println(id);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        System.out.println("Deleting post with ID: " + id);
    }
}
