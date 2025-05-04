package it.epicode.ProgettoSettimanale_S7_L5.eventi;

import it.epicode.ProgettoSettimanale_S7_L5.auth.app_user.AppUser;
import it.epicode.ProgettoSettimanale_S7_L5.prenotazioni.Prenotazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int postiDisponibili;

    @OneToOne(cascade={CascadeType.REMOVE})
    private AppUser appUser;

    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni = new ArrayList<>();
}
