package com.popjun.exception;

import com.popjun.constants.enums.CodeEnum;
import com.popjun.server.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseController {
    @ResponseBody
    @ExceptionHandler
    public ResultVO baseControllerException(Exception ex) {
        //后续改为跳转到500页面
        return new ResultVO(CodeEnum.ERROR.getCode(),ex.getMessage());
    }
}