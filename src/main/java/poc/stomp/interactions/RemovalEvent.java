package poc.stomp.interactions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import poc.stomp.events.CreateEvent;
import poc.stomp.http.notification.EventSender;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RemovalEvent {
    private EventSender<CreateEvent> eventSender;

    public void removal() {
        log.info("REMOVAL");

        var event = CreateEvent.builder().id(UUID.randomUUID()).action("removal").build();
        eventSender.send(event);

    }
}
