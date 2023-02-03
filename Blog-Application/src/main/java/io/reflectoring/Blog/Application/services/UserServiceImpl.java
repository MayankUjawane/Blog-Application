package io.reflectoring.Blog.Application.services;

import io.reflectoring.Blog.Application.entities.User;
import io.reflectoring.Blog.Application.exceptions.ResourceNotFoundException;
import io.reflectoring.Blog.Application.payloads.UserDto;
import io.reflectoring.Blog.Application.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    public UserDto userToDto(User user) {
        //return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAbout());
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        // updating the details of the existing user
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);
        UserDto updatedUserDto = userToDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> dtoList = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User"," Id ",userId));
        userRepo.delete(user);
    }
}
