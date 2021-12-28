package com.loginapp.demo.controller;

import com.loginapp.demo.constants.Roles;
import com.loginapp.demo.dao.UserDao;
import com.loginapp.demo.jwt.JwtProvider;
import com.loginapp.demo.model.User;
import com.loginapp.demo.request.LoginRequest;
import com.loginapp.demo.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
public class LoginController {

    @Value("${secret}")
    private String secret;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        User user = userDao.findByUsername(loginRequest.getUsername());
        Logger.getAnonymousLogger().info(loginRequest.getUsername());
        Logger.getAnonymousLogger().info(loginRequest.getPassword());
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(), loginRequest.getPassword()
                            )
                    );
            return new LoginResponse(jwtProvider.generateJWT(user.getUsername(), ((user != null) && (user.getRole() != null)) ? user.getRole() : Roles.NORMAL_USER, secret, 90000000));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

}
