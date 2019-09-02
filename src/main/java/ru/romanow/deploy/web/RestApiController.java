package ru.romanow.deploy.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanow.deploy.model.SimpleRequest;
import ru.romanow.deploy.model.SimpleResponse;

import javax.validation.Valid;

import static java.lang.String.format;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = { APPLICATION_JSON_UTF8_VALUE, APPLICATION_XML_VALUE })
    public SimpleResponse helloWorld(@Valid @RequestBody SimpleRequest request) {
        String age = request.getAge() != null ? format("Your age is %d", request.getAge()) : "";
        return new SimpleResponse(format("Hello, %s. %s", request.getName(), age));
    }

    // TODO: get & post
}
