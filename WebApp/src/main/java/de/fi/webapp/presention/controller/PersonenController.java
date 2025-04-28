package de.fi.webapp.presention.controller;


import de.fi.webapp.presention.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class PersonenController {

    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})



    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findById(@PathVariable UUID id) {

        if(id.equals(UUID.fromString("12345678-1234-1234-1234-123456789abc")))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PersonDTO.builder().id(id).vorname("John").nachname("Doe").build());
    }


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> findAll(
            @RequestParam(required = false, defaultValue = "Max") String vorname,
            @RequestParam(required = false, defaultValue = "Mustermann")String nachname
    ) {

        System.out.println( vorname + " " + nachname);
        var result = List.of(
                PersonDTO.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build(),
                PersonDTO.builder().id(UUID.randomUUID()).vorname("Jane").nachname("Doe").build()
        );

        return ResponseEntity.ok(result);
    }
    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestBody  PersonDTO personDTO, UriComponentsBuilder builder) {
        UriComponents uriComponents = builder.path("/personen/{id}").buildAndExpand(personDTO.getId());

        System.out.println("Person empfangen: " + personDTO);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> aendern(@PathVariable UUID id, @Valid @RequestBody PersonDTO personDto){
        if(! id.equals(personDto.getId())) throw new IllegalArgumentException("Upps");
        System.out.println("Person geaendert: " + personDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return ResponseEntity.notFound().build();
    }

}
