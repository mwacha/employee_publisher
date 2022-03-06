package tk.mwacha.events.sender;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tk.mwacha.events.EmployeeContent;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmployeeCreatedEvent {
  public static final String VERSION_CONTRACT = "1.0";
  @NonNull private UUID id;
  @NonNull private EmployeeContent content;
}
