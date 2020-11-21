package poc.stomp.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicConfig {
    private String beanName;
    private String topicName;
}