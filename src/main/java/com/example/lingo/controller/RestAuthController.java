package com.example.lingo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.lingo.model.TUser;
import com.example.lingo.service.UserService;
import com.xkcoding.http.config.HttpConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.scope.AuthGoogleScope;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGoogleRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthScopeUtils;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class RestAuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback/{source}")
    public Object login(@PathVariable("source") String source, AuthCallback callback, HttpServletRequest request) {
        System.out.println("进入callback：" + " callback params：" + JSONObject.toJSONString(callback));
        AuthRequest authRequest = getAuthRequest();
        AuthResponse<AuthUser> response = authRequest.login(callback);
        return userService.register(response);
    }

    private AuthRequest getAuthRequest() {
        return new AuthGoogleRequest(AuthConfig.builder()
                .clientId("47159349330-306n7bf3guvbc8jtsvajso1n7k4taano.apps.googleusercontent.com")
                .clientSecret("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")
                .redirectUri("http://localhost:8082/oauth/callback/google")
                .scopes(AuthScopeUtils.getScopes(AuthGoogleScope.USER_EMAIL, AuthGoogleScope.USER_PROFILE, AuthGoogleScope.USER_OPENID))
                // 针对国外平台配置代理
                .httpConfig(HttpConfig.builder()
                        .timeout(15000)
                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                        .build())
                .build());
    }
}