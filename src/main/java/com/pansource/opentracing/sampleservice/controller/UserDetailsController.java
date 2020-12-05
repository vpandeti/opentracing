package com.pansource.opentracing.sampleservice.controller;

import com.pansource.opentracing.sampleservice.model.dto.UserDetailsDto;
import com.pansource.opentracing.sampleservice.service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<UserDetailsDto> findUserDetailsById(@PathVariable("id") String id, Map<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info("key={} value={}", key, value);
        });
        return new ResponseEntity<>(userDetailsService.findUserDetailsById(id), HttpStatus.OK);
    }

}
