package com.lsdi.kafka.listener;

import com.lsdi.kafka.model.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @KafkaListener(topics = "${stream.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(Long message) {
        //System.out.println("Received Message in group 'foo': " + message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = { "0", "3" }),
            containerFactory = "partitionsKafkaListenerContainerFactory")
    public void listenToPartition(@Payload Long message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
    }

    @KafkaListener(topics = "${filtered.topic.name}", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(Long message) {
        System.out.println("Received Message in filtered listener: " + message);
    }

    @KafkaListener(topics = "${greeting.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        System.out.println("Received greeting message: " + greeting.toString());
    }

    @KafkaListener(topics = "${squared.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenSquaredNumbers(Long message) {
        System.out.println("Received Squared Number: " + message);
    }

}
