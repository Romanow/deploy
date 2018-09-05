package ru.romanow.deploy.web;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by romanow on 01.09.17.
 */
@Controller
public class ViewController {

    @GetMapping
    public ModelAndView helloWorld(@RequestParam(value = "name", required = false, defaultValue = "world") String name, HttpServletResponse response) {
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        return new ModelAndView("index", new HashMap<String, String>() {{
            put("name", name);
        }});
    }
}
