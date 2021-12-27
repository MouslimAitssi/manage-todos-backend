package com.loginapp.demo.service;

import com.loginapp.demo.dao.UserDao;
import com.loginapp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("L'utilisateur avec le nom " + username + " est introuvable!");
        }
        ArrayList authorities = new ArrayList<>();
        String role = user.getRole();
        Logger.getAnonymousLogger().info(role);
        authorities.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
}
