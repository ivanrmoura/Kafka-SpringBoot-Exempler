package com.lsdi.kafka;

import com.lsdi.kafka.listener.MessageListener;
import com.lsdi.kafka.model.Greeting;
import com.lsdi.kafka.producer.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaSpringDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaSpringDemoApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        //MessageListener listener = context.getBean(MessageListener.class);

        //publica no tópico number (stream)
        for (int i=1; i <= 100; i++) {
            producer.sendMessage((long) i);
        }


        //producer.sendGreetingMessage(new Greeting("olá, eu sou o naruto","Naruto"));
    }

}
