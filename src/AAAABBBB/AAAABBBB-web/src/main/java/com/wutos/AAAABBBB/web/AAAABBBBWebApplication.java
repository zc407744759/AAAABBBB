package com.wutos.AAAABBBB.web;

import com.github.pagehelper.PageHelper;

import com.wutos.base.App;
import com.wutos.base.common.util.ConfigPath;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;
import java.util.Properties;

@SpringBootApplication
@ComponentScan("com.wutos")
@MapperScan("com.wutos.*.domain.mapper")
@EnableScheduling
public class AAAABBBBWebApplication extends App {

    public static void main(String[] args) {
        ConfigPath configPath1 = new ConfigPath("E:\\gitlab_reposiitory\\AAAABBBB\\conf\\");
        configPath = configPath1;
        loadConfig(args);
        SpringApplication.run(AAAABBBBWebApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AAAABBBBWebApplication.class);
    }

}