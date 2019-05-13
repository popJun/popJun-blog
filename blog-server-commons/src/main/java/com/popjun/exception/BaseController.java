package com.popjun.exception;

import com.popjun.constants.enums.CodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class BaseController {
    @ResponseBody
    @ExceptionHandler
    public Map<String, Object> baseControllerException(Exception ex) {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("code", CodeEnum.ERROR.getCode());
        stringObjectMap.put("message", ex.getMessage());
        log.error("code:"+ CodeEnum.ERROR.getCode()+" message:"+ex.getMessage());
        //后续改为跳转到500页面
        return stringObjectMap;
    }
}