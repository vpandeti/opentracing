package com.pansource.opentracing.sampleservice.service;


import com.pansource.opentracing.sampleservice.exception.UserNotFoundException;
import com.pansource.opentracing.sampleservice.model.dto.BookDto;
import com.pansource.opentracing.sampleservice.model.dto.UserDetailsDto;
import com.pansource.opentracing.sampleservice.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserDetailsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDetailsDto findUserDetailsById(String userId) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        String userResourceUrl = String.format("http://localhost:8081/users/%s", userId);
        ResponseEntity<UserDto> userDtoResponseEntity = get(userResourceUrl, UserDto.class);
        if (userDtoResponseEntity.getStatusCode() != HttpStatus.OK) {
            log.info("userId={} message=user_is_not_found", userId);
            throw new UserNotFoundException(String.format("User is not found with id:%s", userId));
        }
        log.info("userId={} message=user_is_found", userId);
        userDetailsDto.setUser(userDtoResponseEntity.getBody());

        String bookResourceUrl = String.format("http://localhost:8082/books?userId=%s", userId);
        ResponseEntity<BookDto[]> bookDtoResponseEntity = get(bookResourceUrl, BookDto[].class);
        if (bookDtoResponseEntity.getStatusCode() == HttpStatus.OK && Objects.nonNull(bookDtoResponseEntity.getBody())) {
            userDetailsDto.setBooks(Arrays.asList(bookDtoResponseEntity.getBody()));
        }
        return userDetailsDto;
    }

    private <T> ResponseEntity<T> get(String url, Class<T> classType) {
        return restTemplate.getForEntity(url, classType);
    }
}
