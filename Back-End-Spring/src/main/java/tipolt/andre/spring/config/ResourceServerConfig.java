package tipolt.andre.spring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Autowired
    private SecurityFilterConfig securityFilterConfig;

    // private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**",
    // "/products/**", "/users/save" };

    // private static final String[] OPERATOR_OR_ADMIN = { "/products/**",
    // "/categories/**", "/myorders/**" };

    // private static final String[] ADMIN = { "/users/**", "/orders/**" };

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // if(Arrays.asList(env.getActiveProfiles()).contains("test")){
        // http.headers().frameOptions().disable();
        // }
        // http.authorizeRequests(requests -> requests.anyRequest().permitAll());

        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products").hasRole("OPERATOR")
                                .anyRequest().permitAll())
                // .authorizeHttpRequests(authorize -> authorize
                // .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                // .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                // .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                // .anyRequest().authenticated()
                // )
                .addFilterBefore(securityFilterConfig, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfig = new CorsConfiguration();

        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        corsConfig.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
