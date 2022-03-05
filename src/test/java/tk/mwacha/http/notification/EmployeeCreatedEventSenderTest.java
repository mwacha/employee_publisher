package tk.mwacha.http.notification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.Topic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.mwacha.events.sender.EmployeeCreatedEvent;
import tk.mwacha.helpers.EmployeeTestHelper;

@ExtendWith(MockitoExtension.class)
class EmployeeCreatedEventSenderTest {
    @Captor
    private ArgumentCaptor<PublishRequest> captor;

    @Mock
    private AmazonSNS amazonSNS;

    @Mock
    private Topic topic;

    @InjectMocks
    private EmployeeCreatedEventSender eventSender;


    @Test
    public void should_send_event() {
        var event = givenEmployeeCreated();

        eventSender.send(event);

        checkMessage(event);
    }

    private void checkMessage(EmployeeCreatedEvent event) {
        then(amazonSNS).should().publish(captor.capture());

        PublishRequest notification = captor.getValue();

        assertThat(notification).extracting("message").isEqualToComparingOnlyGivenFields(event);
    }

    private EmployeeCreatedEvent givenEmployeeCreated() {
        return EmployeeTestHelper.makeEmployee().toCreatedEvent();
    }

}