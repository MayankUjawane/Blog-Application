package io.reflectoring.Blog.Application.controllers;

import io.reflectoring.Blog.Application.config.Constants;
import io.reflectoring.Blog.Application.payloads.ApiResponse;
import io.reflectoring.Blog.Application.payloads.PostDto;
import io.reflectoring.Blog.Application.payloads.PostResponse;
import io.reflectoring.Blog.Application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    PostService postService;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createdPost = postService.createPost(postDto,categoryId,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> posts = postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = Constants.SORT_ORDER,required = false) String order
    ) {
        PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,order);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse("Post Deleted Successfully",true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    // search posts contains keyword in their title
    @GetMapping("/posts/search/title/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
        List<PostDto> posts = postService.searchPosts(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // search posts contains keyword in their content
    @GetMapping("/posts/search/content/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByContent(@PathVariable String keyword) {
        List<PostDto> posts = postService.searchPostsByContent(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
