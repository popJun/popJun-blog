package com.popjun.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageEnum {
    SUCCESS("成功"),ERROR("服务器错误"),
    SERVICE_REGISTER_SUCCESS("服务注册成功"),SERVICE_REGISTER_ERROR("服务注册失败");
    private String message;

}
