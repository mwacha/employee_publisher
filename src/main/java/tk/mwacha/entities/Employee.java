package tk.mwacha.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import tk.mwacha.events.EmployeeContent;
import tk.mwacha.events.sender.EmployeeCreatedEvent;
import tk.mwacha.events.sender.EmployeeRemovedEvent;
import tk.mwacha.http.dto.EmployeeDTO;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Where(clause = "deleted_at is null")
public class Employee implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String email;
  private String employeeName;
  private LocalDateTime deletedAt;

  public void disable() {
    this.deletedAt = LocalDateTime.now();
  }

  public static Employee of(EmployeeDTO dto) {
    return Employee.builder().employeeName(dto.getEmployeeName()).email(dto.getEmail()).build();
  }

  private EmployeeContent toEmployeeContent() {
    return EmployeeContent.builder().employeeName(employeeName).email(email).build();
  }

  public EmployeeCreatedEvent toCreatedEvent() {
    return EmployeeCreatedEvent.builder().id(getId()).content(toEmployeeContent()).build();
  }

  public EmployeeRemovedEvent toRemovedEvent() {
    return EmployeeRemovedEvent.builder().id(getId()).build();
  }
}
