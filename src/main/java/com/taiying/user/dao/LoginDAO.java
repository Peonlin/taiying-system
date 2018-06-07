package com.taiying.user.dao;

import com.taiying.user.dto.CompanyDTO;
import com.taiying.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginDAO {

    void registerUser(UserDTO userDTO);

    String queryUsernameIsValid(@Param("username") String username);

    UserDTO getUser(@Param("uid") String uid);

    String login(@Param("username") String username, @Param("pwd") String pwd);

    List<CompanyDTO> getCompany();

    String getCompanyName(@Param("id") int id);

    void insertCompany(@Param("companyName") String companyName);

    List<UserDTO> queryAgent();
}
