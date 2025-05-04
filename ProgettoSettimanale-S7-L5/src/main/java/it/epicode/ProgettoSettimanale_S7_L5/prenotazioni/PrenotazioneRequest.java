package it.epicode.ProgettoSettimanale_S7_L5.prenotazioni;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequest {
    @NotNull
    private Long eventoid;
}
