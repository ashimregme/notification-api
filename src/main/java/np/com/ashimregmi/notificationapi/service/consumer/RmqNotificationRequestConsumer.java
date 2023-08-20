package np.com.ashimregmi.notificationapi.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RmqNotificationRequestConsumer {
    @RabbitListener(queues = "notif-q")
    public void send(String inputMessage) {
        log.debug(inputMessage);
    }
}
