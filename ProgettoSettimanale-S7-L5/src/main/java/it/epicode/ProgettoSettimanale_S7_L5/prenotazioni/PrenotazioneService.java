package it.epicode.ProgettoSettimanale_S7_L5.prenotazioni;

import it.epicode.ProgettoSettimanale_S7_L5.auth.app_user.AppUser;
import it.epicode.ProgettoSettimanale_S7_L5.auth.app_user.AppUserRepository;
import it.epicode.ProgettoSettimanale_S7_L5.eventi.Evento;
import it.epicode.ProgettoSettimanale_S7_L5.eventi.EventoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final EventoRepository eventoRepository;
    private final AppUserRepository appUserRepository;

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    public List<Prenotazione> getPrenotazioniUtente() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser appUser = appUserRepository.findByUsername(username) // o findByUsername
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        return prenotazioneRepository.findByAppUserId(appUser.getId());
    }

    public void save(@Valid PrenotazioneRequest request, Long utenteId){
        Evento evento = eventoRepository.findById(request.getEventoid())
                .orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));
        if(evento.getPostiDisponibili() == 0){
            throw new IllegalArgumentException("Posti esauriti");
        }

        AppUser appUser = appUserRepository.findById(utenteId)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        boolean isPrenotato = prenotazioneRepository.existsByAppUserIdAndEventoId(utenteId, request.getEventoid());
        if(isPrenotato) {
            throw new IllegalArgumentException("Evento già prenotato");
        }

        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(request, prenotazione);
        prenotazione.setAppUser(appUser);
        prenotazione.setEvento(evento);
        prenotazione.setDataPrenotazione(LocalDate.now());
        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        prenotazioneRepository.save(prenotazione);
        eventoRepository.save(evento);
    }

    public void deletePrenotazione(Long eventoId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser appUser = appUserRepository.findByUsername(username) // o findByUsername
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        Prenotazione prenotazione = prenotazioneRepository.findByAppUserIdAndEventoId(appUser.getId(), eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));

        Evento evento = prenotazione.getEvento();
        evento.setPostiDisponibili(evento.getPostiDisponibili() + 1);
        prenotazioneRepository.delete(prenotazione);
        eventoRepository.save(evento);
    }

}
