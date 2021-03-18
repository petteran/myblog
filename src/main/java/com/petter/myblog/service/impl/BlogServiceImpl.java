package com.petter.myblog.service.impl;

import com.petter.myblog.entity.Blog;
import com.petter.myblog.mapper.BlogMapper;
import com.petter.myblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
