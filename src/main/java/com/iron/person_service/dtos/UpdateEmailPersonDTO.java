package com.iron.person_service.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmailPersonDTO {

    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "El correo electrónico solo puede contener letras y números antes de '@gmail.com'")
    private String email;
}
