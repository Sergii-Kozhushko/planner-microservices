package de.javabegin.micro.planner.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"de.javabegin.micro.planner"})// доп указываем где искать классы под бины
@EnableJpaRepositories(basePackages = {"de.javabegin.micro.planner.todo"})// доп указываем, где искать джпа
@EnableFeignClients //чтобы использовать фейн для вызова микросервисов
public class PlannerTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerTodoApplication.class, args);
    }

}
