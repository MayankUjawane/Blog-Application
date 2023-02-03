package io.reflectoring.Blog.Application.repositories;

import io.reflectoring.Blog.Application.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
