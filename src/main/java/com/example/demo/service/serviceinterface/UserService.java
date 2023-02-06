package com.example.demo.service.serviceinterface;

import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
     User findUser(String name);

     User findUserByID(int userId);
}
