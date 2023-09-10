package np.com.ashimregmi.notificationapi.service.consumer;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;
import np.com.ashimregmi.notificationapi.service.SendNotificationApi;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class RmqSendConsumer {
    private final SendNotificationApi sendNotificationApi;

    @RabbitListener(queues = "send-q", ackMode = "MANUAL")
    public void receive(String inputMessage,
                        Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.debug(inputMessage);
        SpecificDeviceRmqMessage specificDeviceRmqMessage
                = JsonUtils.fromJson(inputMessage, SpecificDeviceRmqMessage.class);
        sendNotificationApi.send(specificDeviceRmqMessage);
        channel.basicAck(tag, false);
    }
}
