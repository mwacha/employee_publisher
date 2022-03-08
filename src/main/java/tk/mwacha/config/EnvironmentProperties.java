package tk.mwacha.config;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "environment")
public class EnvironmentProperties {
  private List<TopicConfig> topicsConfig;
}
