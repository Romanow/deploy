package ru.romanow.deploy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class ViewController {

    @GetMapping
    public ModelAndView helloWorld(@RequestParam(value = "name", required = false, defaultValue = "world") String name) {
        return new ModelAndView("index", new HashMap<String, String>() {{
            put("name", name);
        }});
    }
}
