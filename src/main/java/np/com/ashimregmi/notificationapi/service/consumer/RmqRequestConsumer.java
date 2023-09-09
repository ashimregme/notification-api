package np.com.ashimregmi.notificationapi.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;
import np.com.ashimregmi.notificationapi.service.BatchCreator;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@RequiredArgsConstructor
public class RmqRequestConsumer {
    private final BatchCreator batchCreator;

    @RabbitListener(queues = "request-q")
    public void receive(String inputMessage) {
        log.debug(inputMessage);

        RequestRmqMessage requestRmqMessage = JsonUtils.fromJson(inputMessage, RequestRmqMessage.class);
        batchCreator.createBatchesAndSend(requestRmqMessage);
    }
}
