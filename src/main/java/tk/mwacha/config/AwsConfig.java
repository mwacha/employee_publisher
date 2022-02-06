package tk.mwacha.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final ConfigurableListableBeanFactory beanFactory;
    private final EnvironmentProperties environment;

    @Bean
    public AWSCredentialsProvider amazonAWSCredentialsProvider(
            @Value("${aws.secret-key}") String secretKey,
            @Value("${aws.access-key}") String accessKeyId) {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretKey));
    }

    @Bean
    @Profile("!development")
    public AmazonSNS snsClient(AWSCredentialsProvider credentialsProvider,  @Value("${aws.region}") String region) {
        var amazonSNS = AmazonSNSClient.builder().withCredentials(credentialsProvider).withRegion(region).build();
        creatTopics(amazonSNS);
        return amazonSNS;
    }

    @Bean
    @Profile("!development")
    public AmazonSQS sqsClient(AWSCredentialsProvider credentialsProvider, @Value("${aws.region}") String region) {
        return AmazonSQSClient.builder().withCredentials(credentialsProvider).withRegion(region).build();
    }


    private void creatTopics(AmazonSNS amazonSNS) {
        environment.getTopicsConfig().forEach(topicConfig -> createTopicBean(amazonSNS, topicConfig));
    }

    private Topic createTopicBean(AmazonSNS amazonSNS, TopicConfig config) {
        var topic = findTopic(amazonSNS, config.getTopicName());
        beanFactory.registerSingleton(config.getBeanName(), topic);
        return topic;
    }

    private Topic findTopic(AmazonSNS amazonSNS, String topicName) {
        ListTopicsResult listTopicsResult;
        String nextToken = null;

        log.info("configuring aws topic {}", topicName);

        do {
            if (nextToken == null) {
                listTopicsResult = amazonSNS.listTopics();
            } else {
                listTopicsResult = amazonSNS.listTopics(nextToken);
            }
            nextToken = listTopicsResult.getNextToken();
            var topicOption =
                    listTopicsResult.getTopics().stream()
                            .filter(topic -> topic.getTopicArn().endsWith(topicName))
                            .findFirst();

            if (topicOption.isPresent()) {
                return topicOption.get();
            }

        } while (nextToken != null);

        throw new IllegalArgumentException("AWS Topic not found " + topicName);
    }
}