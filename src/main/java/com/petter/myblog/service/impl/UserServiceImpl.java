package com.petter.myblog.service.impl;

import com.petter.myblog.entity.User;
import com.petter.myblog.mapper.UserMapper;
import com.petter.myblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anpetter
 * @since 2021-03-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
