package tk.mwacha.service;

import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tk.mwacha.helpers.EmployeeTestHelper;
import tk.mwacha.interactions.EmployeeCreation;
import tk.mwacha.interactions.EmployeeRemoval;

@ExtendWith(MockitoExtension.class)
class CreateRemovalSimulationServiceTest {

  @Mock private EmployeeCreation creation;
  @Mock private EmployeeRemoval removal;

  @InjectMocks private CreateRemovalSimulationService service;

  @Test
  void should_create() {
    var employee = EmployeeTestHelper.makeEmployee();
    service.createRemoval(employee);

    then(creation).should().create(employee);
    then(removal).should().remove(employee.getId());
  }
}
