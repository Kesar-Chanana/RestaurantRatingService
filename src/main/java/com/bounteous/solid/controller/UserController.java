package com.bounteous.solid.controller;

import com.bounteous.solid.entity.AppUser;
import com.bounteous.solid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public AppUser addUser(@RequestBody AppUser user) {
        return userService.addUser(user);
    }
}
