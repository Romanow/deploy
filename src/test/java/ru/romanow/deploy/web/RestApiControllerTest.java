package ru.romanow.deploy.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import ru.romanow.deploy.domain.Person;
import ru.romanow.deploy.model.PersonInfo;
import ru.romanow.deploy.repository.PersonRepository;
import ru.romanow.deploy.service.PersonService;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}