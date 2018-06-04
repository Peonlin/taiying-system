package com.taiying.user.controller;

import com.alibaba.druid.util.StringUtils;
import com.taiying.user.dto.UserDTO;
import com.taiying.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public String loginIntoSystem(@RequestBody UserDTO userDTO) throws Exception {
        return loginService.login(userDTO.getUsername(), userDTO.getPwd());
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) throws Exception {
        loginService.register(userDTO);
        return "success";
    }

    @GetMapping("/user")
    public UserDTO getUser(HttpServletRequest request) {
        String uid = (Arrays.stream(request.getCookies()).filter(cookie -> StringUtils.equals(cookie.getName(), "uid"))).findFirst().get().getValue();
        return loginService.getUser(uid);
    }
}
