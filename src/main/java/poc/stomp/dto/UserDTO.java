package poc.stomp.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String login;
	private String password;
	private String name;
	private Integer rowVersion;
	private Boolean active;
	private Date endDate;
	private String email;
	private String newPassword;


}
