/**
 * UserWebClientBuilder.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-Apr-2023 13:48
 */

package de.javabegin.micro.planner.utils.rest.webclient;

import de.javabegin.micro.planner.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

// указываем, что этот класс является спринг-бином
@Component
public class UserWebClientBuilder {
    private static final String baseUrl = "http://localhost:8765/planner-users/user";
    private static final String baseUrlData = "http://localhost:8765/planner-todo/data/";

    public boolean userExists(Long userId) {
        try {
            // Синхронное выполнение запроса к микросервису
            User user = WebClient.create(baseUrl)
                    .post()
                    .uri("id")
                    .bodyValue(userId)
                    .retrieve()
                    .bodyToFlux(User.class).blockFirst();
            // retreive - непосредственно вызывает микросрвис
            // blockFirst - блокирует поток при получении 1-й записи
            // bodyToFlux - считывание результата асинхронно
            if (user != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Flux<User> userExistsAsync(Long userId){
        Flux<User> fluxUser = WebClient.create(baseUrl)
                .post()
                .uri("/id")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(User.class);
        return fluxUser;
    }

    // иниц. начальных данных
    public Flux<Boolean> initUserData(Long userId) {

        Flux<Boolean> fluxUser = WebClient.create(baseUrlData)
                .post()
                .uri("init")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(Boolean.class);

        return fluxUser;

    }

}
