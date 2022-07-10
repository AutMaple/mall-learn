package com.autmaple.mall.tiny.common.exception;

import com.autmaple.mall.tiny.common.api.IErrorCode;

/**
 * @ClassName ApiException
 * @Description 自定义 API 异常
 * @Author AutMaple
 * @Date 2022/7/10 14:18
 * @Version 1.0
 **/
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
