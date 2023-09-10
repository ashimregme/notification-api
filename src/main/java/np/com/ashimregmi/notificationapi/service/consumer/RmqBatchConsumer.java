package np.com.ashimregmi.notificationapi.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.BatchedRmqMessage;
import np.com.ashimregmi.notificationapi.service.BatchProcessor;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@RequiredArgsConstructor
public class RmqBatchConsumer {
    private final BatchProcessor batchProcessor;
    @RabbitListener(queues = "batch-q")
    public void receive(String inputMessage) {
        log.debug(inputMessage);

        BatchedRmqMessage batchedRmqMessage = JsonUtils.fromJson(inputMessage, BatchedRmqMessage.class);
        batchProcessor.process(batchedRmqMessage);
    }
}
