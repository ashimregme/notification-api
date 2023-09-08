package np.com.ashimregmi.notificationapi.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.service.BatchCreator;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

@Slf4j
@RequiredArgsConstructor
public class RmqNotificationRequestConsumer {
    private final BatchCreator batchCreator;

    @RabbitListener(queues = "request-q")
    public void receive(String inputMessage) {
        log.debug(inputMessage);

        QueuedMessage queuedMessage = JsonUtils.fromJson(inputMessage, QueuedMessage.class);
        batchCreator.createBatchesAndSend(queuedMessage);
    }
}
