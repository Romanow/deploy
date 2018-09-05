package ru.romanow.deploy.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.romanow.deploy.model.SimpleRequest;
import ru.romanow.deploy.model.SimpleResponse;

import javax.validation.Valid;

import static java.lang.String.format;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @PostMapping
    public SimpleResponse helloWorld(@Valid @RequestBody SimpleRequest request) {
        String age = request.getAge() != null ? format("Your age is %d", request.getAge()) : "";
        return new SimpleResponse(format("Hello, %s. %s", request.getName(), age));
    }
}
