package tk.mwacha.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {}
