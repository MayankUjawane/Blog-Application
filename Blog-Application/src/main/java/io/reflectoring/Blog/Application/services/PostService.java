package io.reflectoring.Blog.Application.services;

import io.reflectoring.Blog.Application.payloads.PostResponse;
import io.reflectoring.Blog.Application.payloads.PostDto;

import java.util.List;

public interface PostService {
    // create
    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

    // update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String order);

    // get single post
    PostDto getPostById(Integer postId);

    // get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    // search posts contains keyword in title
    List<PostDto> searchPosts(String keyword);

    // search posts contains keyword in the content
    List<PostDto> searchPostsByContent(String keyword);
}
