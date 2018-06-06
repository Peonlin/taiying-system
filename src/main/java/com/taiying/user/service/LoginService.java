package com.taiying.user.service;


import com.taiying.user.dto.CompanyDTO;
import com.taiying.user.dto.UserDTO;

import java.util.List;


public interface LoginService {

    String register(UserDTO userDTO) throws Exception;

    UserDTO getUser(String uid) throws Exception;

    String login(String username, String pwd) throws Exception;

    List<CompanyDTO> getCompany() throws Exception;
}
