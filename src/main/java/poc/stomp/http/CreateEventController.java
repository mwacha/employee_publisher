package poc.stomp.http;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import poc.stomp.dto.UserDTO;
import poc.stomp.interactions.RemovalEvent;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class CreateEventController {

    private final RemovalEvent interactionRemoval;

    private static List<UserDTO> users = List.of(UserDTO.builder().id(UUID.fromString("dedd8f5a-fc0b-4a4a-a174-38a2010382d1")).name("Marcelo Wacha").build(),
            UserDTO.builder().id(UUID.fromString("2bcfedc5-a6c5-4da2-8b3d-b91acf07422f")).name("Mariana Wacha").build(),
            UserDTO.builder().id(UUID.fromString("a46d8408-f7b6-4ba9-80b6-2676e1cd0105")).name("Luiza Wacha").build(),
            UserDTO.builder().id(UUID.fromString("32545ae0-197a-458a-9319-00dddd9f779a")).name("Fabiana Dantas").build(),
            UserDTO.builder().id(UUID.fromString("d9ba7949-5c2d-48ef-9013-d4abb598d6c3")).name("Rodrigo Wacha").build(),
            UserDTO.builder().id(UUID.fromString("a64dd870-8d2b-4700-ab5e-47b51dbcca9f")).name("Milton Dantas").build());


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<UserDTO> list() {
        return users;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID id) {
        this.users = users.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList());
        interactionRemoval.removal();
    }


}
