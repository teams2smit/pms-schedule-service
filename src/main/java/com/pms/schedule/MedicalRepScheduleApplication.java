package com.pms.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
@EnableEurekaClient
public class MedicalRepScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalRepScheduleApplication.class, args);
    }

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("dd-MMM-yyyy");
    }


    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths((t)->
                        t.matches(".*(/schedule).*"))
                .build()
                .apiInfo(getApiInfo());
    }

    public ApiInfo getApiInfo(){
        return  new ApiInfoBuilder()
                .title("Schedule Service")
                .version("v2.0")
                .build();
    }

}
