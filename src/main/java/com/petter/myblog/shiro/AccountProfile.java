package com.petter.myblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName AccountProfile
 * @DescClass TODO
 * @CreateTime 2021/3/19 19:38
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */

@Data
public class AccountProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;
}
