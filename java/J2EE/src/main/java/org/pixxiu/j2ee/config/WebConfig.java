package org.pixxiu.j2ee.config;


import org.pixxiu.j2ee.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/login", "/user/register")
                .excludePathPatterns("/swagger-ui/**","/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/doc.html/**", "/v3/**");
    }
}
