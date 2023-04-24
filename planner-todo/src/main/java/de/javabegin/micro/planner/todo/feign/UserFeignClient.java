package de.javabegin.micro.planner.todo.feign;

import de.javabegin.micro.planner.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// fallback - предохранитель(circuitbreaker) если недоступен МС planner-users
@FeignClient(name="planner-users", fallback = UserFeignClientFallback.class)// название микро-сервиса в эврике, который будем вызывать
public interface UserFeignClient {
    @PostMapping("/user/id")// адрес такой как в UserController того сервиса который выызваем
    ResponseEntity<User> findUserById(@RequestBody Long id);// параметры тоже

}
@Component
class UserFeignClientFallback implements UserFeignClient{

    // cicruit-breaker method for "/user/id"
    @Override
    public ResponseEntity<User> findUserById(Long id) {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
