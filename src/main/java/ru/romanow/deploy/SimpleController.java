package ru.romanow.deploy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by romanow on 01.09.17.
 */
@Controller
public class SimpleController {

    @GetMapping
    public ModelAndView helloWorld(@RequestParam(value = "name", required = false, defaultValue = "world") String name) {
        return new ModelAndView("index", new HashMap<String, String>() {{
            put("name", name);
        }});
    }
}
