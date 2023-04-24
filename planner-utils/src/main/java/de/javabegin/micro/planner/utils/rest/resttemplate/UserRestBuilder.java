/**
 * UserRestBuilder.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-Apr-2023 12:47
 */

package de.javabegin.micro.planner.utils.rest.resttemplate;

import de.javabegin.micro.planner.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserRestBuilder {
   private static final String baseUrl = "http://localhost:8765/planner-users/user";

   public boolean userExists(Long userId){
      RestTemplate restTemplate = new RestTemplate();// устаревший синхронный способ вызова микросервисов
      HttpEntity<Long> request = new HttpEntity(userId);// это сам запрос, точнее его Body

      ResponseEntity<User> response = null;
      try {
         response = restTemplate.exchange(baseUrl + "/id", HttpMethod.POST, request, User.class);
         if (response.getStatusCode() == HttpStatus.OK){
            return true;
         }
      } catch (Exception e){
         e.printStackTrace();
      }
      return false;
   }

}
