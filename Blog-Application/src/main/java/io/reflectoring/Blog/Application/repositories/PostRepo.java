package io.reflectoring.Blog.Application.repositories;

import io.reflectoring.Blog.Application.entities.Category;
import io.reflectoring.Blog.Application.entities.Post;
import io.reflectoring.Blog.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);

    // when we add findBy with any field then that method will search data in the table
    // having the field value as provided by the user
    List<Post> findByCategory(Category category);

    // if with any field (here field is title) we add findByFieldNameContaining then the
    // containing act as "like" in sql
    List<Post> findByTitleContaining(String title);

    @Query("select p from Post p where p.content like :key")
    List<Post> searchByContent(@Param("key") String content);

}
