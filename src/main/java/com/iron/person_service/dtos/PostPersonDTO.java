package com.iron.person_service.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPersonDTO {

    @NotBlank(message = "El nickname no puede ser nulo")
    private String nickName;

    @NotBlank(message = "El nombre completo no puede ser nulo")
    private String name;

    @NotBlank(message = "El número de teléfono no puede ser nulo")
    @Pattern(regexp = "^\\d{9}$", message = "El número de teléfono debe tener exactamente 9 dígitos")
    private String phoneNumber;

    @NotBlank(message = "El email no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "El correo electrónico debe ser un Gmail válido")
    private String email;
}
