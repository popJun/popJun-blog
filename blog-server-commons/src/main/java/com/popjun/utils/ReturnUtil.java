package com.popjun.utils;

import com.popjun.constants.enums.CodeEnum;
import com.popjun.server.dto.ReturnMessageDTO;
import com.popjun.server.vo.ResultVO;

import java.util.Optional;

public class ReturnUtil {
    public static ResultVO dealResult(ReturnMessageDTO returnMessageDTO){
        return Optional.ofNullable(returnMessageDTO)
                .map(clientResult ->new ResultVO(clientResult.getCode(),clientResult.getMessage()))
                .orElse(new ResultVO(CodeEnum.ERROR.getCode(),"服务器异常"));
    }
}
