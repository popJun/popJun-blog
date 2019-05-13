
package com.popjun.sso.cas.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.popjun.constants.enums.CodeEnum;
import com.popjun.server.dto.ReturnMessageDTO;
import com.popjun.sso.cas.server.api.ClientService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.RegisteredService;
import org.apereo.cas.services.ReturnAllAttributeReleasePolicy;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0", interfaceClass = ClientService.class,retries = 0)
@Component
public class ClientServiceImpl implements ClientService {
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    public ClientServiceImpl() {
    }

    public ReturnMessageDTO addClientService(String serviceId, Integer id) {
        ReturnMessageDTO returnMessage = new ReturnMessageDTO();

        try {
            String urlServiceId = "^(https|imaps|http)://" + serviceId + ".*";
            RegexRegisteredService service = new RegexRegisteredService();
            ReturnAllAttributeReleasePolicy re = new ReturnAllAttributeReleasePolicy();
            service.setServiceId(urlServiceId);
            service.setId((long)id);
            service.setAttributeReleasePolicy(re);
            service.setLogoutUrl(new URL("http://" + serviceId));
            service.setName("自定义客户端02");
            this.servicesManager.save(service);
            this.servicesManager.load();
            returnMessage.setCode(CodeEnum.SUCCESS.getCode());
            returnMessage.setMessage("注册服务成功");
        } catch (Exception var7) {
            returnMessage.setCode(CodeEnum.ERROR.getCode());
            returnMessage.setMessage("注册服务失败");
            var7.printStackTrace();
        }

        return returnMessage;
    }

    public ReturnMessageDTO removeClientService(String serviceId, Integer id) {
        RegisteredService service = this.servicesManager.findServiceBy(serviceId);
        try {
            this.servicesManager.delete(service);
        } catch (Exception var5) {

        }
        this.servicesManager.load();
        ReturnMessageDTO returnMessage = new ReturnMessageDTO();
        returnMessage.setCode(CodeEnum.SUCCESS.getCode());
        returnMessage.setMessage("删除成功");
        return returnMessage;
    }

    @Override
    public List getAllClientService() {
        return new ArrayList<>(servicesManager.getAllServices());
    }
}
