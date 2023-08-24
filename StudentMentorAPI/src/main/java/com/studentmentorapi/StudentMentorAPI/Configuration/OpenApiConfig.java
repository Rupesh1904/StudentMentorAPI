package com.studentmentorapi.StudentMentorAPI.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Mentor Rating API")
                                 .description("The backend APIs of a Web app (that allows users to give rating and review to a\r\n" + //
                                         "mentor and allows mentor to recommend Students(or Letter of appreciation).")
                                 .version("1.0"));
    }
}