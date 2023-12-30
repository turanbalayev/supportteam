package com.turanbalayev.supportteam.resource;

import com.turanbalayev.supportteam.domain.User;
import com.turanbalayev.supportteam.domain.UserPrincipal;
import com.turanbalayev.supportteam.exception.domain.EmailExistException;
import com.turanbalayev.supportteam.exception.domain.ExceptionHandling;
import com.turanbalayev.supportteam.exception.domain.UserNotFoundException;
import com.turanbalayev.supportteam.exception.domain.UsernameExistException;
import com.turanbalayev.supportteam.service.UserService;
import com.turanbalayev.supportteam.utility.JWTTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.turanbalayev.supportteam.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    public UserResource(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(),user.getPassword());

        User loginUser = userService.findUserByUsername(user.getUsername());

        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeaders = getJwtHeader(userPrincipal);

        return new ResponseEntity<>(loginUser,jwtHeaders, OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UsernameExistException, UserNotFoundException, EmailExistException {
        User newUser = userService.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }


    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER,jwtTokenProvider.GenerateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }


}

