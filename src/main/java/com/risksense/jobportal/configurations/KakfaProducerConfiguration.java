package com.risksense.jobportal.configurations;

import com.risksense.jobportal.entity.Job;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static com.risksense.jobportal.configurations.KafKaConstants.BOOTSTRAP_SERVERS_CONFIG;


@Configuration
public class KakfaProducerConfiguration {

    @Bean
    public ProducerFactory<String, Job> producerFactory() {
        Map<String, Object> configuration = new HashMap<>();

        configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
        configuration.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configuration);
    }


    @Bean
    public KafkaTemplate<String, Job> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
