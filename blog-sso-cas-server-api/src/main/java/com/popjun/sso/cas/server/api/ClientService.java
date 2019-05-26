package com.popjun.sso.cas.server.api;

import com.popjun.server.dto.ReturnMessageDTO;

import java.util.List;

/**
 * 暴露dubbo信息添加和移出客户端
 */
public interface ClientService {
     ReturnMessageDTO addClientService(String serviceId, Integer id,String clientName);
     ReturnMessageDTO removeClientService(String serviceId);
     List getAllClientService();
}
