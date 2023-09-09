package np.com.ashimregmi.notificationapi.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;
import np.com.ashimregmi.notificationapi.service.SendNotificationApi;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@RequiredArgsConstructor
public class RmqSendConsumer {
    private final SendNotificationApi sendNotificationApi;

    @RabbitListener(queues = "send-q")
    public void receive(String inputMessage) {
        log.debug(inputMessage);
        SpecificDeviceRmqMessage specificDeviceRmqMessage = JsonUtils.fromJson(
                inputMessage, SpecificDeviceRmqMessage.class);
        sendNotificationApi.send(specificDeviceRmqMessage);
    }
}
