package com.petter.myblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petter.myblog.common.lang.Result;
import com.petter.myblog.entity.Blog;
import com.petter.myblog.service.BlogService;
import com.petter.myblog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author anpetter
 * @since 2021-03-19
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        //分页

        //获取博客数据并且按文章创建时间倒序排列
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        Page<Blog> page = new Page<>(currentPage, 5);
        Page<Blog> pageData = blogService.page(page, wrapper);
        return Result.success(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已经被删除");
        return Result.success(blog);
    }

    //有权限才能编辑
    @RequiresAuthentication
    @GetMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            blogService.getById(blog.getId());
            //编辑只能编辑自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限编辑");
        }else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtils.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }

    @RequiresAuthentication
    @DeleteMapping("/blog/{id}")
    public Result del(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
            blogService.remove(queryWrapper);
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限删除");
        }else {
            return Result.fail("没有删除权限");
        }
        return Result.success(null);
    }
}
