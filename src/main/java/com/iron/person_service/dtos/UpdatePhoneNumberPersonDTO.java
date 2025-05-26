package com.iron.person_service.dtos;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneNumberPersonDTO {

    @Digits(integer = 9, fraction = 0, message = "El número de teléfono debe tener 9 dígitos")
    private int phoneNumber;
}
