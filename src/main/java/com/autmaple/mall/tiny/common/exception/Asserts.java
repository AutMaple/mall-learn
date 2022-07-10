package com.autmaple.mall.tiny.common.exception;

import com.autmaple.mall.tiny.common.api.IErrorCode;

/**
 * @ClassName Asserts
 * @Description 断言处理类，用于抛出各类 API 异常
 * @Author AutMaple
 * @Date 2022/7/10 14:17
 * @Version 1.0
 **/
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
