package com.turanbalayev.supportteam.resource;

import com.turanbalayev.supportteam.domain.HttpResponse;
import com.turanbalayev.supportteam.domain.User;
import com.turanbalayev.supportteam.exception.domain.EmailExistException;
import com.turanbalayev.supportteam.exception.domain.ExceptionHandling;
import com.turanbalayev.supportteam.exception.domain.UserNotFoundException;
import com.turanbalayev.supportteam.exception.domain.UsernameExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {


    @GetMapping("/home")
    public String showUser() throws UsernameExistException {
        //return "Application works";
        throw new UsernameExistException("Brooom menim oyeeee");
    }


}

