package com.petter.myblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.petter.myblog.common.dto.LoginDto;
import com.petter.myblog.common.lang.Result;
import com.petter.myblog.entity.User;
import com.petter.myblog.service.UserService;
import com.petter.myblog.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName AccountController
 * @DescClass TODO
 * @CreateTime 2021/3/23 18:49
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */
@Slf4j
@RestController
public class AccountController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto,
                        HttpServletResponse response) {
        //获取 user 并且判断这个用户是否存在
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        if (user== null && user.equals("")) {
            return Result.fail("用户名不能为空");
        }
        //判断密码是否正确
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }

        //生成 jwt 并且存储在 header 里面
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expost-Headers", "Authorization");

        //返回 user 必要的信息
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
        );
    }
}
