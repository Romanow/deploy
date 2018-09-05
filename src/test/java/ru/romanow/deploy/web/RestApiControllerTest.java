package ru.romanow.deploy.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.romanow.deploy.model.SimpleRequest;

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class RestApiControllerTest {
    private static final String NAME = "Ronin";
    private static final Integer AGE = 28;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new GsonBuilder().create();

    @Test
    void helloWorldWithName() throws Exception {
        SimpleRequest request =
                new SimpleRequest()
                        .setName(NAME);

        mockMvc.perform(post("/api")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value(format("Hello, %s. ", NAME)));
    }

    @Test
    void helloWorldWithNameAndAge() throws Exception {
        SimpleRequest request =
                new SimpleRequest(NAME, AGE);

        mockMvc.perform(post("/api")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value(format("Hello, %s. Your age is %d", NAME, AGE)));
    }

    @Test
    void helloWorldContentNegotiation() throws Exception {
        SimpleRequest request = new SimpleRequest(NAME, AGE);

        mockMvc.perform(post("/api")
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(xpath("//message").string(format("Hello, %s. Your age is %d", NAME, AGE)));
    }
}