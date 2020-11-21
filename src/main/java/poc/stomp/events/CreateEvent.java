package poc.stomp.events;

import java.util.UUID;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class CreateEvent {
    public static final String CURRENT_VERSION = "1.0";
    @NonNull private UUID id;
    @NonNull private String action;


}