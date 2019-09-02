package ru.romanow.deploy.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.romanow.deploy.model.CreatePersonRequest;
import ru.romanow.deploy.model.PersonInfo;
import ru.romanow.deploy.service.PersonService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestApiController {
    private final PersonService personService;

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<PersonInfo> persons(@RequestParam(required = false) String name) {
        List<PersonInfo> persons;
        if (name != null) {
            persons = personService.findByName(name);
        } else {
            persons = personService.getAllPersons();
        }
        return persons;
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createPerson(@Valid @RequestBody CreatePersonRequest request) {
        final URI location = personService.createPerson(request);
        return ResponseEntity.created(location).build();
    }
}
