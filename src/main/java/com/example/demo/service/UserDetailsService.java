package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.serviceinterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements UserService {
    @Autowired
    private UserRepository userImpl;
    @Override
    public User findUser(String name) {
        return userImpl.findUserByUsername(name);
    }
    @Override
    public User findUserByID(int userId) {
        return userImpl.findUserByid(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userImpl.findUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return user;
    }

    public UserDetails loadUserByUsername(int userId) throws UsernameNotFoundException {
        User user = userImpl.findUserByid(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Not Found");
        }
        return user;
    }
}
