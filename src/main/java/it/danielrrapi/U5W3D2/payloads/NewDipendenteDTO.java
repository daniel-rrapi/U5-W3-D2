package it.danielrrapi.U5W3D2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotBlank(message = "Username obbligatorio")
        @Size(min = 3, max = 15, message = "Lo username deve essere minimo 3 caratteri e massimo 15")
        String username,
        @NotBlank(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 15, message = "Il nome deve essere minimo 3 caratteri e massimo 15")
        String name,
        @NotBlank(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 15, message = "Il cognome deve essere minimo 3 caratteri e massimo 15")
        String cognome,
        @NotBlank(message = "L'email è obbligaria")
        @Email(message = "Email non valida")
        String email,
        @NotBlank(message = "Password obbligatoria")
        String password
        ) {
}
