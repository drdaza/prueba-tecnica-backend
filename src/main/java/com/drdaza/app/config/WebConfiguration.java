package com.drdaza.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
    @Override

    public void addCorsMappings(CorsRegistry registry){
        registry
        .addMapping("/**")
        .allowedOrigins("*")
        /* .allowCredentials(true) */
        .allowedMethods("GET", "POST", "DELETE", "OPTION", "PUT");
        //si pongo un allowed origin en especifico tengo que activar el allowcredentials= true de lo contrario no
    }
}
