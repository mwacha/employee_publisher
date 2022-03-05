package tk.mwacha.http.notification;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.Topic;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tk.mwacha.events.sender.EmployeeCreatedEvent;

@Slf4j
@Component
@Profile("!test")
public class EmployeeCreatedEventSender implements EventSender<EmployeeCreatedEvent> {

  private final AmazonSNS amazonSNS;
  private final Topic topic;
  private final Gson gson;

  public EmployeeCreatedEventSender(
      AmazonSNS amazonSNS, @Qualifier("employeeCreated") Topic topic) {
    this.amazonSNS = amazonSNS;
    this.topic = topic;
    this.gson = new Gson();
  }

  @Override
  public void send(EmployeeCreatedEvent event) {
    log.info("SENDING EVENT ID {}", event.getId());
    var request = new PublishRequest();

    request.setMessage(gson.toJson(event));

    request.setTopicArn(topic.getTopicArn());
    amazonSNS.publish(request);

    log.info("EVENT CREATED HAS BEEN SENT ID {}", event.getId());
  }
}
