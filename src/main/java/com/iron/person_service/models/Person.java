package com.iron.person_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "persons")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @NonNull
    @NotBlank(message = "el nickname no puede ser nulo")
    private String nickName;

    @NotBlank(message = "el nombre completo no puede ser nulo")
    @NonNull
    private String name;


    @Digits(integer = 10, fraction = 0, message = "El número de teléfono debe tener 10 dígitos")
    @NonNull
    private int phoneNumber;

    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "El correo electrónico solo puede contener letras y números antes de '@gmail.com'")
    private String email;

}
