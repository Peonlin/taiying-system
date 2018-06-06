package com.taiying.user.controller;

import com.alibaba.druid.util.StringUtils;
import com.taiying.common.util.CacheUtil;
import com.taiying.user.dto.CompanyDTO;
import com.taiying.user.dto.UserDTO;
import com.taiying.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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
    public UserDTO getUser(@CookieValue("uid") String uid) throws Exception {
        return loginService.getUser(uid);
    }

    @GetMapping("/company")
    public List<CompanyDTO> getCompany() throws Exception {
        return loginService.getCompany();
    }

    @GetMapping("/logout")
    public void logout(@CookieValue("uid") String uid) {
        CacheUtil.removeUserFromCache(uid);
    }
}
