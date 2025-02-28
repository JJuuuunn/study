package com.yang.kakaomap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KakaomapApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaomapApplication.class, args);
    }

}
