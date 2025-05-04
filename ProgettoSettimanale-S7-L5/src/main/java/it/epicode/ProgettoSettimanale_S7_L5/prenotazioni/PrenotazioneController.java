package it.epicode.ProgettoSettimanale_S7_L5.prenotazioni;

import it.epicode.ProgettoSettimanale_S7_L5.auth.app_user.AppUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ORGANIZZATORE')")
    @GetMapping("/{id}")
    public Prenotazione findById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ORGANIZZATORE')")
    @GetMapping("/utente")
    public List<Prenotazione> getPrenotazioniUtente() {
        return prenotazioneService.getPrenotazioniUtente();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long eventoId) {
        prenotazioneService.deletePrenotazione(eventoId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid PrenotazioneRequest request, @AuthenticationPrincipal AppUser utenteLoggato) {
        prenotazioneService.save(request, utenteLoggato.getId());
    }
}
