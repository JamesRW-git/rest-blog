package com.example.restblog.web;

import com.example.restblog.data.Post;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value="/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    public List<Post> getAll(){
        List<Post> postList = new ArrayList<>();
        Post postOne = new Post(1L,"Cheese is great","Most people love cheese");
        Post postTwo = new Post(2L,"What is sleep?", "Sleep is the cousin of death");
        Post postThree = new Post(3L,"Javelin vs. T-72BV", "Javelin wins");
        postList.add(postOne);
        postList.add(postTwo);
        postList.add(postThree);

        return postList;
    }

    @GetMapping("/{id}")
    public Post getById(@RequestParam("id") Long id) {
        Post testPost = new Post(4L, "What IS a platypus?", "A platypus is a weird venomous duck beaver.");

        return testPost;
    }

    @PostMapping
    public void createPost(@RequestBody Post newPost) {
        System.out.println(newPost);
    }

}
