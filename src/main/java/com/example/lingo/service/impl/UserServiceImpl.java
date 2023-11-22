package com.example.lingo.service.impl;

import com.example.lingo.common.Result;
import com.example.lingo.dto.LoginInfoDto;
import com.example.lingo.dto.UserInfoDto;
import com.example.lingo.mapper.TUserMapper;
import com.example.lingo.mapper.TUserMemoryMapper;
import com.example.lingo.model.TUser;
import com.example.lingo.model.TUserMemory;
import com.example.lingo.service.UserService;
import com.example.lingo.util.BcryptUtil;
import com.example.lingo.util.JwtUtil;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.example.lingo.enums.ResultCodeEnum.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    TUserMapper userMapper;
    @Autowired
    TUserMemoryMapper userMemoryMapper;
    @Override
    public Result login(LoginInfoDto loginInfoDto) {
        TUser user = userMapper.selectByUsername(loginInfoDto.getUsername());
        if (null == user) {
            Result.fail(USER_NOT_EXISTS);
        }
        if (!BcryptUtil.match(loginInfoDto.getPassword(), user.getPassword())) {
            return Result.fail(USER_PASSWORD_ERROR);
        }

        String jwtToken = jwtUtil.createJwtToken(user.getUserId().toString(), 60*30);
        return Result.success(jwtToken);
    }

    @Override
    public Result register(TUser user) {
        TUser checkUser = userMapper.selectByUsername(user.getUsername());
        if(checkUser != null){
            return Result.fail(USER_EXISTS);
        }
        user.setPassword(BcryptUtil.encode(user.getPassword()));
        user.setNickname("ppp");
        user.setEmail("zzz@email.com");
        user.setCreatedAt(new Date());
        userMapper.insert(user);
        TUser duser = userMapper.selectByUsername(user.getUsername());
        for(int i = 1 ; i <= 2 ; i ++){
            TUserMemory userMemory = new TUserMemory();
            userMemory.setUserId(duser.getUserId());
            userMemory.setWordId(0L);
            userMemory.setTableId(Long.valueOf(i));
            userMemoryMapper.insert(userMemory);
        }
        String jwtToken = jwtUtil.createJwtToken(user.getUserId().toString(), 60*30);
        return Result.success(jwtToken);
    }

    @Override
    public Result register(AuthResponse<AuthUser> authResponse) {
        if(authResponse.ok()){
            var response = authResponse.getData();
            TUser checkUser = userMapper.selectByUsername(response.getEmail());
            if(checkUser != null){
                String jwtToken = jwtUtil.createJwtToken(checkUser.getUserId().toString(), 60*30);
                return Result.success(jwtToken);
            }
            TUser user = new TUser();
            user.setUsername(response.getEmail());
            user.setNickname(response.getNickname());
            user.setPassword(BcryptUtil.encode("123456"));
            user.setCreatedAt(new Date());
            userMapper.insert(user);
            TUser duser = userMapper.selectByUsername(user.getUsername());
            for(int i = 1 ; i <= 2 ; i ++){
                TUserMemory userMemory = new TUserMemory();
                userMemory.setUserId(duser.getUserId());
                userMemory.setWordId(0L);
                userMemory.setTableId(Long.valueOf(i));
                userMemoryMapper.insert(userMemory);
            }
            String jwtToken = jwtUtil.createJwtToken(user.getUserId().toString(), 60*30);
            return Result.success(jwtToken);
        }else{
            return Result.fail(authResponse.getMsg());
        }
    }

    @Override
    public Result getUserData(Long userId) {
        TUser user = userMapper.selectByPrimaryKey(userId);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setNickname(user.getNickname());
        userInfoDto.setGlossary(userMemoryMapper.selectByUserId(userId));
        return Result.success(userInfoDto);
    }
}
