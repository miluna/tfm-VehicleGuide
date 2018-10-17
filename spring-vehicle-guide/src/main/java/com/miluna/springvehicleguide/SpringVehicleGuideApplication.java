package com.miluna.springvehicleguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableWebMvc
@EnableEurekaServer
@SpringBootApplication
public class SpringVehicleGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringVehicleGuideApplication.class, args);
    }
}