package io.reflectoring.Blog.Application.repositories;

import io.reflectoring.Blog.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
