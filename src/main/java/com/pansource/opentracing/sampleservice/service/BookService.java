package com.pansource.opentracing.sampleservice.service;

import com.pansource.opentracing.sampleservice.model.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BookService {

    private final Map<String, List<BookDto>> bookDtoMap;

    public BookService() {
        bookDtoMap = new HashMap<>();
        bookDtoMap.put("4890-12-29379490", Collections.singletonList(new BookDto("2357-12-29379490", "A Dance with Dragons", "George.R.R.Martin")));
        bookDtoMap.put("5890-12-29379490", Collections.singletonList(new BookDto("3357-12-29379490", "A Feast for Crows", "George.R.R.Martin")));
        bookDtoMap.put("6890-12-29379490", Collections.singletonList(new BookDto("4357-12-29379490", "A Song of Ice and Fire", "George.R.R.Martin")));
    }

    public List<BookDto> findBooksByUserId(String id) {
        if (bookDtoMap.containsKey(id)) {
            log.info("userId={} message=books_found", id);
            return bookDtoMap.get(id);
        }
        log.info("userId={} message=no_books_found", id);
        return Collections.emptyList();
    }
}
