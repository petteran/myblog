package com.petter.myblog.controller;


import com.petter.myblog.entity.User;
import com.petter.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anpetter
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("{id}")
    public Object getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }


}
