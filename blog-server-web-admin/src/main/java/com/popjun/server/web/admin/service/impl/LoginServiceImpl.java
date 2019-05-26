package com.popjun.server.web.admin.service.impl;

import com.popjun.constants.enums.UrlEnum;
import com.popjun.server.web.admin.service.LoginService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Map getInfoForToken(HttpServletRequest request, String token) {
        final AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
        return Optional.ofNullable(principal).map(p -> {
            Map infoMap = new HashMap<>();
            if (p instanceof AttributePrincipal) {
                Map<String, Object> attributes = p.getAttributes();
                infoMap.put("name", attributes.get("loginName"));
                infoMap.put("roles", attributes.get("roles"));
                infoMap.put("avatar", "https://img2.woyaogexing.com/2019/04/29/51f21df4536d4baa8687bc87410b2e70!400x400.jpeg");
            }
            return infoMap;
        }).orElse(new HashMap<>());
    }

    @Override
    public void redirectLogin(HttpServletResponse response, HttpSession session) throws IOException {
            session.invalidate();
            response.sendRedirect(UrlEnum.CAS_SERVER_URL.getUrl() + "/cas/logout?service=" + UrlEnum.INTERFACE_URL.getUrl());
    }

}
