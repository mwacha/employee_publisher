package tk.mwacha.http;

import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mwacha.entities.Employee;
import tk.mwacha.http.dto.EmployeeDTO;
import tk.mwacha.interactions.EmployeeCreation;
import tk.mwacha.interactions.EmployeeRemoval;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class EmployeeController {

    private EmployeeRemoval interactionRemoval;
    private EmployeeCreation interactionCreation;


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID id) {
        interactionRemoval.remove(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody EmployeeDTO dto) {
        interactionCreation.create(Employee.of(dto));
    }

}
