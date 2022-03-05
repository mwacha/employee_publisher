package tk.mwacha.http.notification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.Topic;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.mwacha.events.sender.EmployeeRemovedEvent;

@ExtendWith(MockitoExtension.class)
class EmployeeRemovalEventSenderTest {
  @Captor private ArgumentCaptor<PublishRequest> captor;

  @Mock private AmazonSNS amazonSNS;

  @Mock private Topic topic;

  @InjectMocks private EmployeeRemovalEventSender eventSender;

  @Test
  public void should_send_event() {
    var event = givenEmployeeRemove();
    UUID randomUUID = UUID.randomUUID();
    eventSender.send(event);

    try (MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class)) {
      uuidMockedStatic.when(() -> UUID.randomUUID()).thenReturn(randomUUID);
      eventSender.send(event);
    }

    checkMessage(event, randomUUID);
  }

  private void checkMessage(EmployeeRemovedEvent event, UUID randomUUID) {
    then(amazonSNS).should(times(2)).publish(captor.capture());

    PublishRequest notification = captor.getValue();

    assertThat(notification).extracting("message").isEqualToComparingOnlyGivenFields(event);
  }

  private EmployeeRemovedEvent givenEmployeeRemove() {
    return EmployeeRemovedEvent.builder().id(UUID.randomUUID()).build();
  }
}
