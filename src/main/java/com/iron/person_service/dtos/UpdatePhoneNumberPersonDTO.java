package com.iron.person_service.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneNumberPersonDTO {

    @NotBlank(message = "El número de teléfono no puede ser nulo")
    @Pattern(regexp = "^\\d{9}$", message = "El número de teléfono debe tener exactamente 9 dígitos")
    private String phoneNumber;
}
