package com.retail.Retail.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path.category}")
    private String uploadPathCategory;

    @Value("${upload.path.product}")
    private String uploadPathProduct;









    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/*");
        registry.addResourceHandler("/static/styles/**")
                .addResourceLocations("classpath:/static/styles/*");
        registry.addResourceHandler("/static/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/blocks/**")
                .addResourceLocations("classpath:/blocks/");
        registry.addResourceHandler("/plugins/**")
                .addResourceLocations("classpath:/AdminLTE-3.2.0/plugins/");
        registry.addResourceHandler("/dist/**")
                .addResourceLocations("classpath:/AdminLTE-3.2.0/dist/");
        registry.addResourceHandler("/category-image/**")
                .addResourceLocations("file://" + uploadPathCategory + "/");
        registry.addResourceHandler("/product-image/**")
                .addResourceLocations("file://" + uploadPathProduct + "/");

    }


}
