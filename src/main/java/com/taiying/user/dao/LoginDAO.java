package com.taiying.user.dao;

import com.taiying.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDAO {

    void registerUser(UserDTO userDTO);

    String queryUsernameIsValid(@Param("username") String username);

    UserDTO getUser(@Param("uid") String uid);

    String login(@Param("username") String username, @Param("pwd") String pwd);
}
