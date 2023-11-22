package com.example.lingo.mapper;

import com.example.lingo.model.TUser;
import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(TUser record);


    TUser selectByPrimaryKey(Long userId);
    TUser selectByUsername(String username);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);
}