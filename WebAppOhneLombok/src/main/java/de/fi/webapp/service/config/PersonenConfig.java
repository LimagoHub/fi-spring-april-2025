package de.fi.webapp.service.config;

import de.fi.webapp.persistence.PersonenRepository;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.internal.PersonenServiceImpl;
import de.fi.webapp.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class PersonenConfig {

    @Bean
    @Qualifier("blacklist")
    @Lazy
    @Scope("singleton")
    public List<String> getBlacklist() {
        return List.of("Attila", "Peter", "Paul", "Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> getFruits() {
        return List.of("Banana", "Strawberry", "Cherry", "Raspberry");
    }

    @Bean
    public PersonenService createPersonenService(final PersonenRepository repo, final PersonMapper mapper, @Qualifier("blacklist") final List<String> antipathen) {
        return new PersonenServiceImpl(repo, mapper, antipathen);
    }
}
