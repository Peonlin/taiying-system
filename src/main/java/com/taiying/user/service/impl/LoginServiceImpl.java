package com.taiying.user.service.impl;

import com.taiying.common.util.CacheUtil;
import com.taiying.common.util.MD5Utils;
import com.taiying.user.dao.LoginDAO;
import com.taiying.user.dto.CompanyDTO;
import com.taiying.user.dto.UserDTO;
import com.taiying.user.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDAO loginDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String register(UserDTO userDTO) throws Exception{
        String uid = loginDAO.queryUsernameIsValid(userDTO.getUsername());
        if (StringUtils.isNotBlank(uid)) {
            throw new Exception("用户名已存在");
        } else {
            uid = UUID.randomUUID().toString().replaceAll("-", "");
        }
        try {
            int companyId = Integer.valueOf(userDTO.getCompany());
            userDTO.setCompany(loginDAO.getCompanyName(companyId));
        } catch (NumberFormatException e) {
            loginDAO.insertCompany(userDTO.getCompany());
        }
        userDTO.setUid(uid);
        userDTO.setPwd(MD5Utils.encrypt(userDTO.getPwd()));
        loginDAO.registerUser(userDTO);
        return uid;
    }

    @Override
    public UserDTO getUser(String uid) throws Exception {
        UserDTO u = CacheUtil.getUser(uid);
        if (u == null) {
            throw new Exception("unlogined");
        }
        return u;
    }

    @Override
    public String login(String username, String pwd) throws Exception {
        String uid = loginDAO.login(username, MD5Utils.encrypt(pwd));
        if (uid == null) {
            throw new Exception("用户名不存在");
        }
        CacheUtil.addUserToCache(loginDAO.getUser(uid));
        return uid;
    }

    @Override
    public List<CompanyDTO> getCompany() throws Exception {
        return loginDAO.getCompany();
    }

    @Override
    public List<UserDTO> getAgent() throws Exception {
        return loginDAO.queryAgent();
    }
}
