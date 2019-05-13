package com.popjun.server.service.admin.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.popjun.server.service.admin.api.SayHello;
import com.popjun.constants.Constant;
import org.springframework.stereotype.Component;
// 注意这里加了一个retries = 0，默认情况下超时时dubbo会自动重掉三次，这样的重复调用很容易导致一些问题，所以这里用retries = 0来关闭超时重试功能。
@Service(version = Constant.Dubbo.DUBBO_VERSION_ADMIN, interfaceClass = SayHello.class,retries = 0)
@Component
public class SayHelloImpl implements SayHello {
    @Override
    public String sayHello(){
        return "hello,dobbo";
    }
}
