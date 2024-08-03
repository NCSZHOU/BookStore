package com.zand.bookstore.common;

import lombok.Data;

@Data
public class CommonResult<T> {
    private int code;
    private String msg;
    private T data;

    public CommonResult() {
    }

    /**
     * response
     *
     * @param msg, response code, data
     * @return
     */
    public static CommonResult response(int responseCode, String msg, Object data) {
        CommonResult result = new CommonResult();
        result.setCode(responseCode);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}

