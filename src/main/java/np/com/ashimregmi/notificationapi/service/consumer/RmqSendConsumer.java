package np.com.ashimregmi.notificationapi.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.QueuedMessageWithToken;
import np.com.ashimregmi.notificationapi.service.SendNotificationApi;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

@Slf4j
@RequiredArgsConstructor
public class RmqSendConsumer {
    private final SendNotificationApi sendNotificationApi;

    @RabbitListener(queues = "send-q")
    public void receive(String inputMessage) {
        log.debug(inputMessage);
        QueuedMessageWithToken queuedMessageWithToken = JsonUtils.fromJson(
            inputMessage, QueuedMessageWithToken.class);
        sendNotificationApi.send(queuedMessageWithToken);
    }
}
