package tipolt.andre.spring.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;

@Component
public class JwtTokenEnhacer implements TokenEnhancer{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserModel user = userRepository.findByEmail(authentication.getName()).get();

        Map<String, Object> map = new HashMap<>();

        map.put("userFirstName", user.getName());
        map.put("userId", user.getId());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;

        token.setAdditionalInformation(map);

        return accessToken;
    }

    

    
}
