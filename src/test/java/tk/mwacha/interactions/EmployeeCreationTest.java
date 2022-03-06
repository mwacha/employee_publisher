package tk.mwacha.interactions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.mwacha.entities.Employee;
import tk.mwacha.events.sender.EmployeeCreatedEvent;
import tk.mwacha.helpers.EmployeeTestHelper;
import tk.mwacha.http.notification.EventSender;
import tk.mwacha.mapper.MessageMapper;
import tk.mwacha.repositories.EmployeeRepository;
import tk.mwacha.repositories.NotificationsRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeCreationTest {

  @Captor private ArgumentCaptor<Employee> captor;

  @Mock private EventSender<EmployeeCreatedEvent> eventSender;
  @Mock private EmployeeRepository repository;
  @Mock private NotificationsRepository notificationsRepository;
  @Mock private MessageMapper mapper;

  @InjectMocks private EmployeeCreation interaction;

  @Test
  void should_create() {
    var employee = EmployeeTestHelper.makeEmployee();
    interaction.create(employee);

    then(repository).should().save(captor.capture());

    assertThat(captor.getValue())
        .isSameAs(employee)
        .extracting("employeeName", "email")
        .containsExactly(employee.getEmployeeName(), employee.getEmail());

    then(eventSender).should().send(employee.toCreatedEvent());
  }
}
