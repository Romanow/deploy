package ru.romanow.deploy;

import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365
 * openssl pkcs12 -export -in cert.pem -inkey key.pem -out cert_and_key.p12 -CAfile chain.pem -caname root
 * keytool -importkeystore -deststorepass secret -destkeypass secret -destkeystore keystore.jks -srckeystore cert_and_key.p12 -srcstoretype PKCS12 -srcstorepass secret
 * keytool -import -trustcacerts -alias root -file chain.pem -keystore keystore.jks
 */
@SpringBootApplication
public class Application
        extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.addUpgradeProtocol(new Http2Protocol()));
        return factory;
    }
}
