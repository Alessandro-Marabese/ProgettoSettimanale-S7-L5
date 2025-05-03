package it.epicode.ProgettoSettimanale_S7_L5.eventi;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class EventoRunner implements CommandLineRunner {
    private final EventoRepository eventoRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {
        if(eventoRepository.count() == 0) {
            for(int i = 0; i < 10; i++) {
                Evento evento = new Evento();
                evento.setTitolo(faker.book().title());
                evento.setDescrizione(faker.book().author());
                LocalDate dataEvento = faker.date().birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                evento.setData(dataEvento);
                evento.setLuogo(faker.address().city());
                evento.setPostiDisponibili(faker.random().nextInt(50));
                eventoRepository.save(evento);
            }
        }

    }
}
