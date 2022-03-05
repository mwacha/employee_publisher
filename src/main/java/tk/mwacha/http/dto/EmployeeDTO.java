package tk.mwacha.http.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;
  private String employeeName;
  private String email;
}
