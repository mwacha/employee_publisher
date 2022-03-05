package tk.mwacha.events.sender;

import java.util.UUID;
import lombok.*;
import tk.mwacha.events.EmployeeContent;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreatedEvent {
  public static final String VERSION_CONTRACT = "1.0";
  @NonNull private UUID id;
  @NonNull private EmployeeContent content;
}
