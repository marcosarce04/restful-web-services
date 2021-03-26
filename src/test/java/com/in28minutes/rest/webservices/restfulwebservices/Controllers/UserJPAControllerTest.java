package com.in28minutes.rest.webservices.restfulwebservices.Controllers;

import com.in28minutes.rest.webservices.restfulwebservices.Beans.User;
import com.in28minutes.rest.webservices.restfulwebservices.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserJPAControllerTest {



    @Test
    void createUser() throws URISyntaxException {


        UserRepository userRepository = mock(UserRepository.class);
        Date birthDate = new Date();
        User user = new User(12, "Marcos", birthDate);
        when(userRepository.save(argThat(user1 -> {
            assertEquals(12, user1.getId());
            assertEquals("Marcos", user1.getName());
            assertEquals(birthDate, user1.getBirthDate());
            return true;
        }))).thenReturn(user);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/users"));

        UserJPAController controller = new UserJPAController(userRepository, null);

        ResponseEntity<Object> result = controller.createUser(user, request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(new URI("http://localhost:8080/users/12"), result.getHeaders().getLocation());
    }
}