package com.zand.bookstore.exception;

import com.zand.bookstore.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseBody
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("[handleValidationExceptions]", ex);
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String filedName = ((org.springframework.validation.FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            sb.append(filedName).append(":").append(errorMessage).append(";");
        });
        return CommonResponse.response(HttpStatus.BAD_REQUEST.value(), sb.toString(), null);
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResponse<?> defaultExceptionHandler(Throwable ex) {
        log.error("[defaultExceptionHandler]", ex);
        return CommonResponse.response(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), null);
    }
}
