package com.pansource.opentracing.sampleservice.controller;


import com.pansource.opentracing.sampleservice.model.dto.UserDto;
import com.pansource.opentracing.sampleservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id, @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info("key={} value={}", key, value);
        });

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

}
