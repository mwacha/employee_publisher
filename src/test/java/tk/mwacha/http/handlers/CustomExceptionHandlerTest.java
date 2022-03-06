package tk.mwacha.http.handlers;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tk.mwacha.exceptions.EntityNotFoundException;
import tk.mwacha.http.EmployeeController;
import tk.mwacha.interactions.EmployeeCreation;
import tk.mwacha.interactions.EmployeeRemoval;
import tk.mwacha.service.CreateRemovalSimulationService;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

  private static final Long TENANT_ID = 1L;
  private static final String CONTEXT_ROOT = "/api/v1/employees";
  @Autowired private MockMvc mockMvc;

  @Mock private EmployeeRemoval interactionRemoval;
  @Mock private EmployeeCreation interactionCreation;
  @Mock private CreateRemovalSimulationService service;

  @InjectMocks private EmployeeController controller;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new CustomExceptionHandler())
            .build();
  }

  @Test
  void should_handle_employee_error() throws Exception {

    var id = UUID.randomUUID();
    doThrow(EntityNotFoundException.class).when(interactionRemoval).remove(id);
    mockMvc
        .perform(delete(CONTEXT_ROOT + "/" + id).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andReturn();
  }
}
