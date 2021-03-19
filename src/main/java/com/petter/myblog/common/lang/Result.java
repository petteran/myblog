package com.petter.myblog.common.lang;

import lombok.Data;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName Result
 * @DescClass TODO
 * @CreateTime 2021/3/19 15:50
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */


@Data
public class Result {
    private int code;// 200是正常，非200表示异常
    private String msg;
    private Object data;


    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {

        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {

        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


}
