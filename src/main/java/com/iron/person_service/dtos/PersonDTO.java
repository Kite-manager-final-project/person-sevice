package com.iron.person_service.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @NotBlank(message = "el nombre completo no puede ser nulo")
    private String name;


    @Digits(integer = 9, fraction = 0, message = "El número de teléfono debe tener 9 dígitos")
    private int phoneNumber;

    @Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "El correo electrónico solo puede contener letras y números antes de '@gmail.com'")
    private String email;
}
