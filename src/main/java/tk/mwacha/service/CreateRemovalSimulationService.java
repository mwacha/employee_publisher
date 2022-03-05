package tk.mwacha.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mwacha.entities.Employee;
import tk.mwacha.interactions.EmployeeCreation;
import tk.mwacha.interactions.EmployeeRemoval;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateRemovalSimulationService {

  private final EmployeeCreation creation;
  private final EmployeeRemoval removal;

  public void createRemoval(Employee employee) {
    creation.create(employee);
    removal.remove(employee.getId());
  }
}
