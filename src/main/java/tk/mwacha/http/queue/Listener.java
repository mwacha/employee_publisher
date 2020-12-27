package tk.mwacha.http.queue;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import tk.mwacha.events.CreateEvent;
import tk.mwacha.http.notification.Notification;

@Slf4j
@Service
@AllArgsConstructor
public class Listener {

    private static final String VERSION_CONTRACT = "1.0";
    private final Gson gson = new Gson();

    @JmsListener(
            destination = "${queue.user.created.name}",
            concurrency = "${queue.user.created.pool-size}")
    public void consumerQueue(String snsNotification) {


            var notification = gson.fromJson(
                    snsNotification,
                    Notification.class);
            var event = gson.fromJson(
                    notification.getMessage(),
                    CreateEvent.class); ;

            log.info("Load event ID {} ACTION {}", event.getId(), event.getAction());



    }
}
