package tipolt.andre.spring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private Environment env;

    private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**", "/products/**", "/users/save" };

    private static final String[] OPERATOR_OR_ADMIN = { "/products/**", "/categories/**", "/myorders/**" };

    private static final String[] ADMIN = { "/users/**", "/orders/**" };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }
        http.authorizeRequests()
            // .anyRequest().permitAll();
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).authenticated()
                .antMatchers(OPERATOR_OR_ADMIN).hasAnyAuthority("OPERATOR", "ADMIN")
                .antMatchers(ADMIN).hasAuthority("ADMIN")
                .anyRequest().authenticated();
    }

}
