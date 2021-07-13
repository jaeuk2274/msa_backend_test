/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naradrama.easyboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"io.naradrama.easyboard"})
@EnableJpaRepositories(basePackages = {"io.naradrama.easyboard"})
public class EasyboardBootApplication {
    //
    public static void main(String[] args) {
        //
        SpringApplication.run(EasyboardBootApplication.class, args);
    }
}
