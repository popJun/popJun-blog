package com.popjun.server.web.admin.controller;
import com.popjun.constants.enums.CodeEnum;
import com.popjun.constants.enums.UrlEnum;
import com.popjun.exception.BaseController;
import com.popjun.server.vo.ResultVO;
import com.popjun.server.web.admin.service.LoginService;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController  extends BaseController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "user/redirectLogin")
    public ModelAndView redirectLogin() {
      return new ModelAndView("redirect:"+ UrlEnum.INTERFACE_URL.getUrl()+"?token="+AbstractCasFilter.CONST_CAS_ASSERTION);
    }

    @RequestMapping(value = "user/info")
    public @ResponseBody ResultVO getUserWithToken(HttpServletRequest request , String token,HttpServletResponse response,HttpSession session) throws IOException {
        Map infoMap = Optional.ofNullable(token).map(t -> loginService.getInfoForToken(request,t)).orElse(new HashMap<>());
        if (infoMap.isEmpty()){
            loginService.redirectLogin(response,session);
        }
        return new ResultVO(CodeEnum.SUCCESS.getCode(),infoMap);
    }
    @RequestMapping("user/logout")
    public void logout(HttpServletResponse response,HttpSession session) throws Exception {
        loginService.redirectLogin(response,session);
    }
}
