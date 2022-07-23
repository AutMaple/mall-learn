package com.autmaple.mall.tiny.common.exception;

import com.autmaple.mall.tiny.common.api.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author AutMaple
 * @Date 2022/7/23 09:30
 * @Version 1.0
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public CommonResult handle(ApiException e){
        if(e.getErrorCode() != null)
            return CommonResult.failed(e.getErrorCode());
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null)
                message = fieldError.getField() + fieldError.getDefaultMessage();
        }
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(BindException.class)
    public CommonResult handleValidException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null)
                message = fieldError.getField() + fieldError.getDefaultMessage();
        }
        return CommonResult.validateFailed(message);
    }
}
