package tk.mwacha.events.sender;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class EmployeeRemovedEvent {
  public static final String VERSION_CONTRACT = "1.0";
  @NonNull private UUID id;
}
