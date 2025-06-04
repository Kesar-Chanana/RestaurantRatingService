package com.bounteous.solid.services;

import com.bounteous.solid.entity.AppUser;
import com.bounteous.solid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public AppUser addUser(AppUser user) {
        return userRepository.save(user);
    }

    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}


