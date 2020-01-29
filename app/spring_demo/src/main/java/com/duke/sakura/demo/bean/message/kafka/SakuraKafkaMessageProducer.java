package com.duke.sakura.demo.bean.message.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SakuraKafkaMessageProducer {
	
	private KafkaTemplate<String, String> kafkaTemplate;
}
