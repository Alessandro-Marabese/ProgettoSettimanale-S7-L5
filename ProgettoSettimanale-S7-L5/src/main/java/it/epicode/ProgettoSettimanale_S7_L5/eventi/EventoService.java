package it.epicode.ProgettoSettimanale_S7_L5.eventi;

import it.epicode.ProgettoSettimanale_S7_L5.auth.app_user.AppUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Validated
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;

    public Evento findById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    public Page<EventoResponse> findAll(Pageable pageable) {return eventoRepository.findAll(pageable).map(this::fromEntity);}

    public Evento save(@Valid EventoRequest request, @AuthenticationPrincipal AppUser utenteLoggato) {
        Evento evento = new Evento();
        BeanUtils.copyProperties(request, evento);
        evento.setAppUser(utenteLoggato);
        return eventoRepository.save(evento);
    }

    public Evento update(Long id, @RequestBody EventoRequest request, @AuthenticationPrincipal AppUser utenteLoggato) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));
        BeanUtils.copyProperties(request, evento);
            return eventoRepository.save(evento);
    }

    public EventoResponse fromEntity(Evento evento) {
        EventoResponse response = new EventoResponse();
        BeanUtils.copyProperties(evento, response);
        return response;
    }
}
