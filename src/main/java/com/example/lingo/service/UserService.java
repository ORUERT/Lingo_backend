package com.example.lingo.service;

import com.example.lingo.common.Result;
import com.example.lingo.dto.LoginInfoDto;
import com.example.lingo.model.TUser;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;

public interface UserService {
//    public
    Result login(LoginInfoDto loginInfoDto);
    Result register(TUser user);
    Result register(AuthResponse<AuthUser> authResponse);

    Result getUserData(Long userId);
}
