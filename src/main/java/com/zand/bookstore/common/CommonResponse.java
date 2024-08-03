package com.zand.bookstore.common;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private int code;
    private String msg;
    private T data;

    public CommonResponse() {
    }

    /**
     * response
     *
     * @param msg, response code, data
     * @return
     */
    public static CommonResponse response(int responseCode, String msg, Object data) {
        CommonResponse result = new CommonResponse();
        result.setCode(responseCode);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}

