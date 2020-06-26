package com.user.manager.configuration;

import com.user.manager.model.User;
import com.user.manager.principal.UserPrincipal;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserPrincipal user= (UserPrincipal) authentication.getPrincipal();
        Map<String,Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
        if(user.getUsername()!=null){
            info.put("username",user.getUsername());
        }


        DefaultOAuth2AccessToken customAccessToken=new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);
        return super.enhance(customAccessToken,authentication);
    }
}
