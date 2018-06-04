package com.taiying.user.service;


import com.taiying.user.dto.UserDTO;


public interface LoginService {

    String register(UserDTO userDTO) throws Exception;

    UserDTO getUser(String uid);

    String login(String username, String pwd) throws Exception;
}
