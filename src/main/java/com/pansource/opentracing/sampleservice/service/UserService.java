package com.pansource.opentracing.sampleservice.service;

import com.pansource.opentracing.sampleservice.exception.UserNotFoundException;
import com.pansource.opentracing.sampleservice.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    private final Map<String, UserDto> userDtoMap;

    public UserService() {
        userDtoMap = new HashMap<>();
        userDtoMap.put("4890-12-29379490", new UserDto("4890-12-29379490", "John", "Snow"));
        userDtoMap.put("5890-12-29379490", new UserDto("4890-12-29379490", "Arya", "Stark"));
        userDtoMap.put("6890-12-29379490", new UserDto("4890-12-29379490", "Bran", "Stark"));
    }

    public UserDto findUserById(String id) {
        if (userDtoMap.containsKey(id)) {
            log.info("userId={} message=user_is_found", id);
            return userDtoMap.get(id);
        }
        log.info("userId={} message=user_is_not_found", id);
        throw new UserNotFoundException(String.format("User is not found with id:%s", id));
    }
}
