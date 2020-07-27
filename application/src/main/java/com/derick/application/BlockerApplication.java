package com.derick.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = {"com.derick"})
@EntityScan("com.derick.domain")
@EnableJpaRepositories(basePackages={"com.derick.repository"})

public class BlockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockerApplication.class, args);
    }

}
