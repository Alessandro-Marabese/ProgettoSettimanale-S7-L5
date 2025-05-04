package it.epicode.ProgettoSettimanale_S7_L5.prenotazioni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneResponse {
    private Long id;
    private LocalDate dataPrenotazione;
    private String nomeEvento;

    public PrenotazioneResponse(Prenotazione prenotazione) {
        this.id = prenotazione.getId();
        this.dataPrenotazione = prenotazione.getDataPrenotazione();
        this.nomeEvento = prenotazione.getEvento().getTitolo();
    }
}
