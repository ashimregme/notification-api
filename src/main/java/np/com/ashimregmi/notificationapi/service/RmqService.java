package np.com.ashimregmi.notificationapi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RmqService implements RmqApi {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String exchangeName, String routingKey, String body) {
        log.debug("exchange: {}, routingKey: {}, body: {}", exchangeName, routingKey, body);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, body);
    }
}
