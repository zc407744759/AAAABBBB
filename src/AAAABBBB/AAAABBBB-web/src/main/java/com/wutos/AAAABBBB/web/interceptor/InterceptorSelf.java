package com.wutos.AAAABBBB.web.interceptor;

import com.wutos.base.common.util.ParsePaths;
import com.wutos.base.web.interceptor.AuthInterceptorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorSelf implements WebMvcConfigurer {
    @Value("${interceptor.paths}")
    private String extraPaths;

    @Value("${interceptor.ignorePaths}")
    private String ignorePaths;
    @Bean
    public AuthInterceptorConfig getAuthInterceptorConfig(){
        return new AuthInterceptorConfig();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Auth认证拦截
        registry.addInterceptor(getAuthInterceptorConfig()).addPathPatterns(ParsePaths.parsePathsToList(extraPaths))
                .excludePathPatterns(ParsePaths.parsePathsToArray(ignorePaths));
//        // 参数解析拦截
//        registry.addInterceptor(new PermissionsInterceptorConfig()).addPathPatterns("/**").excludePathPatterns(ParsePaths.parsePathsToArray(ignorePaths));
    }
}
