package it.epicode.ProgettoSettimanale_S7_L5.eventi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/eventi")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class EventoController {
    private final EventoService eventoService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Evento findById(Long id) {
        return eventoService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public Page<EventoResponse> findAll(@ParameterObject Pageable pageable) {
        return eventoService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento save(@RequestBody @Valid EventoRequest request) {
        return eventoService.save(request);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @RequestBody EventoRequest request) {
        return eventoService.update(id, request);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZZATORE')")
    @DeleteMapping("/{id}")
    public void deleteById(Long id) {
        eventoService.deleteById(id);
    }
}
