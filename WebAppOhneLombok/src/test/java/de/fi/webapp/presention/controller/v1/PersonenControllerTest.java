package de.fi.webapp.presention.controller.v1;

import de.fi.webapp.presention.dto.PersonDTO;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonenService personenServiceMock;

    @Test
    void test1() throws Exception{

        final Optional<Person> optPerson = Optional.of(new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"), "John", "Doe"));
        when(personenServiceMock.findeAnhandId(any())).thenReturn(optPerson);

        PersonDTO ergebnis = restTemplate.getForObject("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", PersonDTO.class);
        assertEquals("John", ergebnis.getVorname());
        verify(personenServiceMock, times(1)).findeAnhandId(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"));
    }

    @Test
    void test2() throws Exception{

        final Optional<Person> optPerson = Optional.of(new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"), "John", "Doe"));
        when(personenServiceMock.findeAnhandId(any())).thenReturn(optPerson);

        String ergebnis = restTemplate.getForObject("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", String.class);
        System.out.println(ergebnis);
    }

    @Test
    void test3() throws Exception{

        final Optional<Person> optPerson = Optional.of(new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"), "John", "Doe"));
        when(personenServiceMock.findeAnhandId(any())).thenReturn(optPerson);

        var ergebnis = restTemplate.getForEntity("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", PersonDTO.class);

        var body = ergebnis.getBody();
        assertEquals("John", body.getVorname());
        assertEquals(200, ergebnis.getStatusCodeValue());
        verify(personenServiceMock, times(1)).findeAnhandId(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"));
    }

    @Test
    void test4() throws Exception{

        final Optional<Person> optPerson = Optional.empty();
        when(personenServiceMock.findeAnhandId(any())).thenReturn(optPerson);

        var ergebnis = restTemplate.getForEntity("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", PersonDTO.class);


        assertEquals(404, ergebnis.getStatusCodeValue());
        verify(personenServiceMock, times(1)).findeAnhandId(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"));
    }

    @Test
    void test5() throws Exception{


        PersonDTO toUpload = new PersonDTO(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb14"), "John", "Doe");

        HttpEntity<PersonDTO> request = new HttpEntity<>(toUpload);

        var liste = List.of(
                new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"), "John", "Doe"),
                new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"), "John", "Wayne"),
                new Person(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13"), "John", "Rambo")
                );



        when(personenServiceMock.findeAlle()).thenReturn(liste);


        var ergebnis = restTemplate.exchange("/v1/personen", HttpMethod.GET, request, new ParameterizedTypeReference<List<PersonDTO>>() { });

        assertEquals(200, ergebnis.getStatusCodeValue());
        assertEquals(ergebnis.getBody().size(), 3);

    }
}