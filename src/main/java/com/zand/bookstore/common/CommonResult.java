package com.zand.bookstore.common;

import lombok.Data;

@Data
public class CommonResult<T> {
    private String code;
    private String msg;
    private T data;

    public CommonResult() {
    }

    public CommonResult(T data) {
        this.data = data;
    }

    /**
     * success , no data response
     *
     * @return
     */
    public static CommonResult success() {
        CommonResult result = new CommonResult();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    /**
     * error, no data response
     * @Param msg
     *
     * @param msg
     * @return
     */
    public static CommonResult error( String msg) {
        CommonResult result = new CommonResult();
        result.setCode("500");
        result.setMsg(msg);
        return result;
    }
}

