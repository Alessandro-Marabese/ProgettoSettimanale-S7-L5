package it.epicode.ProgettoSettimanale_S7_L5.prenotazioni;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.ProgettoSettimanale_S7_L5.auth.app_user.AppUser;
import it.epicode.ProgettoSettimanale_S7_L5.eventi.Evento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataPrenotazione;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
}
