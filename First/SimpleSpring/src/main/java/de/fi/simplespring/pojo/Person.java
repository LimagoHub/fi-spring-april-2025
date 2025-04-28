package de.fi.simplespring.pojo;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {


    private String vorname;
    private String nachname;


}
