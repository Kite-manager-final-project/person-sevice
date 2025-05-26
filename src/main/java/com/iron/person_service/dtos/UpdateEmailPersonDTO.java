package com.iron.person_service.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmailPersonDTO {

    @Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "El correo electrónico solo puede contener letras y números antes de '@gmail.com'")
    private String email;
}
