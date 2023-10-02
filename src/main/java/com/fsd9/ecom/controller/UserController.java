package com.fsd9.ecom.controller;

import com.fsd9.ecom.common.model.EOUser;
import com.fsd9.ecom.common.service.EOUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EOUserService userService;

    @GetMapping(path = "/all")
    public List<EOUser> userList(){
       return this.userService.getAllUsers();
    }

}
