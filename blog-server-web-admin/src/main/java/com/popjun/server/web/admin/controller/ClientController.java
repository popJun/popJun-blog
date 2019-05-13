package com.popjun.server.web.admin.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.popjun.constants.Constant;
import com.popjun.constants.enums.CodeEnum;
import com.popjun.server.dto.ReturnMessageDTO;
import com.popjun.server.vo.ResultVO;
import com.popjun.sso.cas.server.api.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ClientController{
    @Reference(version = Constant.Dubbo.VERSION_SSO)
    ClientService clientService;

    @RequestMapping("cas/add")
    public ResultVO addClientService(String serviceId, Integer id){
        ReturnMessageDTO returnMessageDTO = clientService.addClientService(serviceId, id);
        return new ResultVO(returnMessageDTO.getCode(),returnMessageDTO.getMessage());
    }
    @RequestMapping("cas/remove")
    public ResultVO removeClientService(String serviceId, Integer id){
        ReturnMessageDTO returnMessageDTO = clientService.removeClientService(serviceId, id);
        return new ResultVO(returnMessageDTO.getCode(),returnMessageDTO.getMessage());
    }
    @RequestMapping("cas/getAll")
    public  ResultVO getAllClientService(){
        List allClientService = clientService.getAllClientService();
        return new ResultVO(CodeEnum.SUCCESS.getCode(),JSONUtil.toJsonStr(allClientService));
    }

}