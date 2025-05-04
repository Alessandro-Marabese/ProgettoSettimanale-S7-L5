package it.epicode.ProgettoSettimanale_S7_L5.prenotazioni;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByAppUserIdAndEventoId(Long appUserId, Long eventoId);

    List<Prenotazione> findByAppUserId(Long appUserId);

    Optional<Prenotazione> findByAppUserIdAndEventoId(Long appUserId, Long eventoId);
}
