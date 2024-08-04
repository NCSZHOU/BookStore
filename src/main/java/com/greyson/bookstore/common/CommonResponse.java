package com.greyson.bookstore.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse<T> {
    private int code;
    private String msg;
    private T data;

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

