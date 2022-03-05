package tk.mwacha.http;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tk.mwacha.entities.Employee;
import tk.mwacha.helpers.EmployeeTestHelper;
import tk.mwacha.http.handlers.CustomExceptionHandler;
import tk.mwacha.interactions.EmployeeCreation;
import tk.mwacha.interactions.EmployeeRemoval;
import tk.mwacha.service.CreateRemovalSimulationService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

  private static final String CONTEXT_ROOT = "/api/v1/employees";

  private MockMvc mockMvc;
  @Mock private EmployeeRemoval interactionRemoval;
  @Mock private EmployeeCreation interactionCreation;
  @Mock private CreateRemovalSimulationService service;

  @InjectMocks private EmployeeController controller;

  @BeforeEach
  public void setup() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new CustomExceptionHandler())
            .build();
  }

  @Test
  void should_remove_employee() throws Exception {
    UUID id = UUID.randomUUID();

    mockMvc.perform(delete(CONTEXT_ROOT + "/" + id)).andExpect(status().isNoContent()).andReturn();

    then(interactionRemoval).should().remove(id);
  }

  @Test
  void should_create_employee() throws Exception {
    var json = EmployeeTestHelper.getEmployeeoDtoJsonContent();
    mockMvc
        .perform(post(CONTEXT_ROOT).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();

    then(interactionCreation).should().create(any(Employee.class));
  }

  @Test
  void should_create_removal_employee() throws Exception {
    var json = EmployeeTestHelper.getEmployeeoDtoJsonContent();

    mockMvc
        .perform(
            post(CONTEXT_ROOT + "/createRemoval")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andReturn();

    then(service).should().createRemoval(any(Employee.class));
  }
}
