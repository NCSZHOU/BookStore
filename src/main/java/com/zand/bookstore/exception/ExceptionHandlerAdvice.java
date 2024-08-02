package com.zand.bookstore.exception;

import com.zand.bookstore.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseBody
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("[handleValidationExceptions]",ex);
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String filedName = ((org.springframework.validation.FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            sb.append(filedName).append(":").append(errorMessage).append(";");
        });
        return CommonResult.error(sb.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> defaultExceptionHandler(Throwable ex) {
        log.error("[defaultExceptionHandler]", ex);
        return CommonResult.error("Internal error");
    }
}
