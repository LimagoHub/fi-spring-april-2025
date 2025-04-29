package de.fi.webapp.presention.controller.v1;


import de.fi.webapp.presention.dto.PersonDTO;
import de.fi.webapp.presention.mapper.PersonDTOMapper;
import de.fi.webapp.service.PersonenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {

    private final PersonenService personenService;
    private final PersonDTOMapper mapper;

    public PersonenController(final PersonenService personenService, final PersonDTOMapper mapper) {
        this.personenService = personenService;
        this.mapper = mapper;
    }

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
    public ResponseEntity<PersonDTO> findById(@PathVariable UUID id) throws Exception{
        return ResponseEntity.of(personenService.findeAnhandId(id).map(mapper::convert));
    }


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> findAll(
            @RequestParam(required = false, defaultValue = "Max") String vorname,
            @RequestParam(required = false, defaultValue = "Mustermann")String nachname
    ) throws Exception{

        System.out.println( vorname + " " + nachname);


        return ResponseEntity.ok(mapper.convert(personenService.findeAlle()));
    }
    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestBody  PersonDTO personDTO, UriComponentsBuilder builder) throws Exception{

        personenService.speichern(mapper.convert(personDTO));
        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(personDTO.getId());


        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> aendern(@PathVariable UUID id, @Valid @RequestBody PersonDTO personDto) throws Exception{
        if(! id.equals(personDto.getId())) throw new IllegalArgumentException("Upps");

        personenService.speichern(mapper.convert(personDto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws Exception {

        if(personenService.loeschen(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

}
