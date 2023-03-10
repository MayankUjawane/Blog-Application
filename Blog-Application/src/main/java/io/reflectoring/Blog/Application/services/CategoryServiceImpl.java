package io.reflectoring.Blog.Application.services;

import io.reflectoring.Blog.Application.entities.Category;
import io.reflectoring.Blog.Application.exceptions.ResourceNotFoundException;
import io.reflectoring.Blog.Application.payloads.CategoryDto;
import io.reflectoring.Blog.Application.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        categoryRepo.save(category);
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        boolean isExist = categoryRepo.existsById(categoryId);
        if(isExist==false) {
            throw new ResourceNotFoundException("Category","category id",categoryId);
        }

        Category category = modelMapper.map(categoryDto,Category.class);
        category.setId(categoryId);

        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        boolean isExist = categoryRepo.existsById(categoryId);
        if(isExist==false) {
            throw new ResourceNotFoundException("Category","category id",categoryId);
        }
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));

        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());

        return categoryDtos;
    }
}
