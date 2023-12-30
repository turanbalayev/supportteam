package com.turanbalayev.supportteam.service;

import com.turanbalayev.supportteam.domain.User;
import com.turanbalayev.supportteam.exception.domain.EmailExistException;
import com.turanbalayev.supportteam.exception.domain.UserNotFoundException;
import com.turanbalayev.supportteam.exception.domain.UsernameExistException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {


    User register(String firstName,String lastName,String username,String email) throws UserNotFoundException, EmailExistException, UsernameExistException;

    List<User> getUsers();

    User findUserByUsername(String username);
    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage);
    User updateUser(String currentUsername,String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage);

    void deleteUser(long id);

    void resetPassword(String email);

    User updateProfileImage(String username, MultipartFile profileImage);

}
