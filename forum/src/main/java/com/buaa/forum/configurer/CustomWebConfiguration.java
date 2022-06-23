package com.buaa.forum.configurer;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CustomWebConfiguration implements WebMvcConfigurer {
    @Value("${web.upload-path}")
    private String imageFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 注意如果filePath是写死在这里，一定不要忘记尾部的/或者\\，这样才能读取其目录下的文件
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + imageFilePath);
    }
}