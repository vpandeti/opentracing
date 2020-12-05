package com.pansource.opentracing.sampleservice.controller;


import com.pansource.opentracing.sampleservice.model.dto.BookDto;
import com.pansource.opentracing.sampleservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooksByUserId(@PathParam("userId") String userId, @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info("key={} value={}", key, value);
        });
        return new ResponseEntity<>(bookService.findBooksByUserId(userId), HttpStatus.OK);
    }

}
