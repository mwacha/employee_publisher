package tk.mwacha.interactions;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.mwacha.entities.Employee;
import tk.mwacha.events.sender.EmployeeRemovedEvent;
import tk.mwacha.exceptions.EntityNotFoundException;
import tk.mwacha.helpers.EmployeeTestHelper;
import tk.mwacha.http.notification.EventSender;
import tk.mwacha.repositories.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeRemovalTest {

  @Mock private EventSender<EmployeeRemovedEvent> eventSender;
  @Mock private EmployeeRepository repository;

  @InjectMocks private EmployeeRemoval removal;

  @Test
  void should_remove() {
    var employee = EmployeeTestHelper.makeEmployee();
    given(repository.findById(employee.getId())).willReturn(Optional.of(employee));

    removal.remove(employee.getId());

    then(repository).should().save(employee);
    then(eventSender).should().send(employee.toRemovedEvent());
  }

  @Test
  void should_not_remove_intern_notfound() {
    var employee = EmployeeTestHelper.makeEmployee();
    given(repository.findById(employee.getId())).willReturn(Optional.empty());

    var id = employee.getId();

    assertThatThrownBy(() -> removal.remove(id)).isInstanceOf(EntityNotFoundException.class);

    then(repository).should(never()).save(any(Employee.class));
  }
}
