package tk.mwacha.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import tk.mwacha.mapper.MapperFactory;

@Slf4j
@UtilityClass
public class ParseJsonHelper {

  private static final ObjectMapper MAPPER = MapperFactory.create();

  public <T> T toObject(String content, Class<T> valueType) {
    try {
      return MAPPER.readValue(content, valueType);
    } catch (Exception e) {
      log.error(
          "PARSE ERROR TO OBJECT, CLASS {}, content {} : msg {}",
          valueType,
          content,
          e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public String toJson(Object content) {
    try {
      return MAPPER.writeValueAsString(content);
    } catch (Exception e) {
      log.error(
          "PARSE ERROR TO JSON, CLASS {}, content {} : msg {}",
          content,
          content.getClass(),
          e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
