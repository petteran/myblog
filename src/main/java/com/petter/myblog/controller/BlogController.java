package com.petter.myblog.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author anpetter
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        System.err.println("hello");
        return "hello";
    }
}
