package de.fi.webapp.service.config;

import de.fi.webapp.YamlPropertySourceFactory;
import de.fi.webapp.service.internal.MailServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value= "classpath:mail.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    private String smtp;
    private String username;
    private String password;

    public void setSmtp(final String smtp) {
        this.smtp = smtp;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Bean
    public MailServiceImpl createMailService() {
        return new MailServiceImpl(smtp, username, password);
    }
}
