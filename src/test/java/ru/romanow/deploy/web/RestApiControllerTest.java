package ru.romanow.deploy.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.romanow.deploy.model.CreatePersonRequest;
import ru.romanow.deploy.model.PersonInfo;
import ru.romanow.deploy.service.PersonService;

import java.net.URI;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class RestApiControllerTest {
    private static final String NAME1 = "Ronin";
    private static final String NAME2 = "Alex";
    private static final Integer AGE = 29;

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new GsonBuilder().create();

    @Test
    void personsSuccess()
            throws Exception {
        final PersonInfo person1 = new PersonInfo(NAME1, AGE);
        final PersonInfo person2 = new PersonInfo(NAME2, AGE);
        when(personService.getAllPersons())
                .thenReturn(newArrayList(person1, person2));

        mockMvc.perform(get("/api/v1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(NAME1))
                .andExpect(jsonPath("$[0].age").value(AGE))
                .andExpect(jsonPath("$[1].name").value(NAME2))
                .andExpect(jsonPath("$[1].age").value(AGE));
    }

    @Test
    void personsFindSuccess()
            throws Exception {
        final PersonInfo person = new PersonInfo(NAME1, AGE);
        when(personService.findByName(eq(NAME1))).thenReturn(newArrayList(person));

        mockMvc.perform(get("/api/v1?name=" + NAME1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(NAME1))
                .andExpect(jsonPath("$[0].age").value(AGE))
                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void personsNotFound()
            throws Exception {
        when(personService.getAllPersons()).thenReturn(newArrayList());

        mockMvc.perform(get("/api/v1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void createPerson()
            throws Exception {
        final CreatePersonRequest person = new CreatePersonRequest(NAME1, AGE);
        final URI url = URI.create("/api/v1/1");
        when(personService.createPerson(eq(person))).thenReturn(url);
        mockMvc.perform(post("/api/v1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(person)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}