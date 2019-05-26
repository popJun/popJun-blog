package com.popjun.server.web.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.popjun.annotation.NeedLog;
import com.popjun.constants.Constant;
import com.popjun.constants.enums.CodeEnum;
import com.popjun.exception.BaseController;
import com.popjun.server.dto.ReturnMessageDTO;
import com.popjun.server.vo.ResultVO;
import com.popjun.sso.cas.server.api.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class ClientController extends BaseController {
    @Reference(version = Constant.Dubbo.VERSION_SSO)
    ClientService clientService;

    @RequestMapping("cas/add")
    @NeedLog
    public ResultVO addClientService(String serviceId, Integer id,String clientName){
        ReturnMessageDTO returnMessageDTO = clientService.addClientService(serviceId, id,clientName);
        return new ResultVO(returnMessageDTO.getCode(),returnMessageDTO.getMessage());
    }
    @RequestMapping("cas/remove")
    @NeedLog
    public ResultVO removeClientService(String serviceId){
        ReturnMessageDTO returnMessageDTO = clientService.removeClientService(serviceId);
        return new ResultVO(returnMessageDTO.getCode(),returnMessageDTO.getMessage());
    }
    @RequestMapping("cas/getAll")
    @NeedLog
    public  ResultVO getAllClientService(){
        return new ResultVO(CodeEnum.SUCCESS.getCode(),Collections.singletonMap("items",clientService.getAllClientService()));
    }

}