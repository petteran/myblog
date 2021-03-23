package com.petter.myblog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName LoginDto
 * @DescClass TODO
 * @CreateTime 2021/3/23 18:51
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */

@Data
public class LoginDto implements Serializable {


    @NotBlank(message = "昵称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
