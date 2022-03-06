package tk.mwacha.http.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.mwacha.exceptions.EntityNotFoundException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseBody
  public String onErrorNotFoundEntity(EntityNotFoundException exception) {
    return exception.getMessage();
  }
}
