package it.epicode.ProgettoSettimanale_S7_L5.eventi;

import it.epicode.ProgettoSettimanale_S7_L5.auth.app_user.AppUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/eventi")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class EventoController {
    private final EventoService eventoService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ORGANIZZATORE')")
    @GetMapping("/{id}")
    public Evento findById(@PathVariable Long id) {
        return eventoService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ORGANIZZATORE')")
    @GetMapping
    public Page<EventoResponse> findAll(@ParameterObject Pageable pageable) {
        return eventoService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento save(@RequestBody @Valid EventoRequest request, @AuthenticationPrincipal AppUser utenteLoggato) {
        return eventoService.save(request, utenteLoggato);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @RequestBody EventoRequest request, @AuthenticationPrincipal AppUser utenteLoggato) {
        return eventoService.update(id, request, utenteLoggato);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        eventoService.deleteById(id);
    }
}
