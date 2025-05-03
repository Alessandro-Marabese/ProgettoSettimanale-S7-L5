package it.epicode.ProgettoSettimanale_S7_L5.eventi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoRequest {
    @NotBlank(message = "Titolo is mandatory")
    private String titolo;
    @NotBlank(message = "Descrizione is mandatory")
    private String descrizione;
    @NotNull(message = "Data is mandatory")
    private LocalDate data;
    @NotBlank(message = "Luogo is mandatory")
    private String luogo;
    @NotNull(message = "Posti Disponibili is mandatory")
    private int postiDisponibili;
}
