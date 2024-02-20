package it.danielrrapi.U5W3D2.payloads;

import it.danielrrapi.U5W3D2.customValidators.EnumValidator;
import it.danielrrapi.U5W3D2.entities.Dipendente;
import it.danielrrapi.U5W3D2.enums.DispositivoStatus;
import it.danielrrapi.U5W3D2.enums.DispositivoType;
import jakarta.validation.constraints.NotNull;

public record NewDispositivoDTO(
        @EnumValidator(enumClazz = DispositivoType.class, message = "Enum errato")
        String type,
        @EnumValidator(enumClazz = DispositivoStatus.class, message = "Enum errato")
        String status,
        @NotNull
        Dipendente dipendente
        ) {
}
