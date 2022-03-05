package tk.mwacha.helpers;


import lombok.experimental.UtilityClass;
import tk.mwacha.entities.Employee;
import tk.mwacha.http.dto.EmployeeDTO;

import java.util.UUID;

@UtilityClass
public class EmployeeTestHelper {


  public String getEmployeeoDtoJsonContent() {
    var pathFile = "mock/json/employee-dto.json";
    return FileContentTestHelper.getContentAsString(pathFile);
  }


  public EmployeeDTO makeEmployeeDTO() {
    return ParseJsonHelper.toObject(getEmployeeoDtoJsonContent(), EmployeeDTO.class);
  }

  public Employee makeEmployee() {
    var employee = Employee.of(makeEmployeeDTO());
    employee.setId(UUID.fromString("061f3ee0-5728-11ea-ae66-fbfeef893134"));
    return employee;
  }


}
