package com.zy;

import com.zy.app.rating.standard.main.plugin.PluginsConfig;
import com.zy.rest.JacksonConfig;
import com.zy.rest.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import({PluginsConfig.class, JacksonConfig.class, SwaggerConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}