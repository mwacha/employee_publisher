package tk.mwacha.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import tk.mwacha.exceptions.CanNotParseException;

@Slf4j
@AllArgsConstructor
public class MessageMapper {
    private final ObjectMapper objectMapper;

    public String toJson(@Validated Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new CanNotParseException();
        }
    }
}
