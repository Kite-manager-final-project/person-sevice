package com.iron.person_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @NotBlank(message = "el nickname no puede ser nulo")
    private String nickName;

    @NotBlank(message = "el nombre completo no puede ser nulo")
    private String name;


    @Digits(integer = 9, fraction = 0, message = "El número de teléfono debe tener 9 dígitos")
    private int phoneNumber;


    @Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "El correo electrónico solo puede contener letras y números antes de '@gmail.com'")
    @NotBlank(message = "el email no puede ser nulo")
    private String email;

}
