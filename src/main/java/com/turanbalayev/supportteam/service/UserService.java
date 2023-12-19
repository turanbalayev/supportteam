package com.turanbalayev.supportteam.service;

import com.turanbalayev.supportteam.domain.User;
import com.turanbalayev.supportteam.exception.domain.EmailExistException;
import com.turanbalayev.supportteam.exception.domain.UserNotFoundException;
import com.turanbalayev.supportteam.exception.domain.UsernameExistException;

import java.util.List;

public interface UserService {


    User register(String firstName,String lastName,String username,String email) throws UserNotFoundException, EmailExistException, UsernameExistException;

    List<User> getUsers();

    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
