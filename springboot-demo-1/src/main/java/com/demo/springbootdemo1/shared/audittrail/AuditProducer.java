//package com.demo.springbootdemo1.shared.audit;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuditProducer {
//
//    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;
//    private final String topic;
//
//    public AuditProducer(KafkaTemplate<String, AuditEvent> kafkaTemplate,
//                         @Value("${audit.topic}") String topic) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.topic = topic;
//    }
//
//    public void publish(AuditEvent auditEvent) {
//        kafkaTemplate.send(topic, auditEvent.getEventId(), auditEvent);
//    }
//}
