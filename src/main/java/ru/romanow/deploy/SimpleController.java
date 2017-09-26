package ru.romanow.deploy;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by romanow on 01.09.17.
 */
@Controller
public class SimpleController {

    /**
     * openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365
     * openssl pkcs12 -export -in cert.pem -inkey key.pem -out cert_and_key.p12 -CAfile chain.pem -caname root
     * keytool -importkeystore -deststorepass secret -destkeypass secret -destkeystore keystore.jks -srckeystore cert_and_key.p12 -srcstoretype PKCS12 -srcstorepass secret
     * keytool -import -trustcacerts -alias root -file chain.pem -keystore keystore.jks
     */
    @GetMapping
    public ModelAndView helloWorld(@RequestParam(value = "name", required = false, defaultValue = "world") String name, HttpServletResponse response) {
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        return new ModelAndView("index", new HashMap<String, String>() {{
            put("name", name);
        }});
    }
}
