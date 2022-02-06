package tk.mwacha.http.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String employeeName;
	private String email;
}
