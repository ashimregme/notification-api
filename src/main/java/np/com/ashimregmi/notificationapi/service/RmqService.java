package np.com.ashimregmi.notificationapi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RmqService implements RmqApi {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String exchangeName, String routingKey, String body) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    }
}
