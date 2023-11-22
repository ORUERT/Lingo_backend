package com.example.lingo.shiro;

import com.example.lingo.mapper.TUserMapper;
import com.example.lingo.model.TUser;
import com.example.lingo.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TUserMapper tUserMapper;
    /**
     * 限定这个realm只能处理JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权(授权部分这里就省略了)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取到用户名，查询用户权限
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        // 获取token信息
        System.out.println("doGetAuthenticationInfo");
        String token = (String) authenticationToken.getCredentials();
        // 校验token：未校验通过或者已过期
        if (jwtUtil.isExpire(token)) {
            throw new AuthenticationException("token已失效，请重新登录");
        }
        //这里实际上没有验证token的正确性，只是验证了token是否过期
        // 用户信息
        TUser user = tUserMapper.selectByPrimaryKey(Long.valueOf(jwtUtil.getUserId(token)));
        if (null == user) {
            throw new UnknownAccountException("用户不存在");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, token, this.getName());
        return simpleAuthenticationInfo;
    }
}
