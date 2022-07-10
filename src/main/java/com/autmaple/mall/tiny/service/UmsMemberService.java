package com.autmaple.mall.tiny.service;

import com.autmaple.mall.tiny.common.api.CommonResult;

/**
 * 会员管理 Service
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     *
     * @param telephone 电话号码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号是否匹配
     *
     * @param telephone 电话号码
     */
    CommonResult verifyAuthCode(String telephone, String autCode);

}
