package com.zy.rest;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.ResponseMessage;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;


@Configuration
public class SwaggerConfig {

    @Autowired
    SpringSwaggerConfig swagger;


    @Bean
    public SwaggerSpringMvcPlugin customImplementation(){

        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(swagger)
                .apiInfo(new ApiInfo("Zy REST API", null, null, null, null, null))
                .includePatterns(".*api.*")
                .apiVersion("0.0.1");

        List<ResponseMessage> responseMessages = Arrays.asList(
                new ResponseMessage(200, "Success", null),
                new ResponseMessage(500, "Internal error", null));
        swaggerSpringMvcPlugin.globalResponseMessage(RequestMethod.GET, responseMessages);
        swaggerSpringMvcPlugin.globalResponseMessage(RequestMethod.POST, responseMessages);

        return swaggerSpringMvcPlugin;
    }
}
