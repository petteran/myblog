package com.petter.myblog.util;

import com.petter.myblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName ShiroUtil
 * @DescClass TODO
 * @CreateTime 2021/3/19 19:37
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */

public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
