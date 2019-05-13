package com.popjun.server.web.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public interface LoginService {
    Map getInfoForToken(HttpServletRequest request, String token);
    void redirectLogin(HttpServletResponse response, HttpSession session) throws IOException;
}
