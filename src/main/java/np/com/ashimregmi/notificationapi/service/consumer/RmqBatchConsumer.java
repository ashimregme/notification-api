package np.com.ashimregmi.notificationapi.service.consumer;

import java.util.List;

import np.com.ashimregmi.notificationapi.service.RmqApi;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.dto.BatchQueuedMessage;
import np.com.ashimregmi.notificationapi.dto.QueuedMessageWithToken;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

@Slf4j
@RequiredArgsConstructor
public class RmqBatchConsumer {
    private final UsersDao usersDao;
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;

    @RabbitListener(queues = "batch-q")
    public void receive(String inputMessage) {
        log.debug(inputMessage);

        BatchQueuedMessage batchQueuedMessage = JsonUtils.fromJson(
                inputMessage, BatchQueuedMessage.class);
        List<String> deviceTokens = usersDao.getDeviceTokens(
                batchQueuedMessage.getQueuedMessage(),
                batchQueuedMessage.getFrom(),
                batchQueuedMessage.getLimit());

        deviceTokens.forEach(deviceToken -> rmqApi.send(
                exchangeName,
                routingKey,
                JsonUtils.toJson(new QueuedMessageWithToken(
                        batchQueuedMessage.getQueuedMessage(),
                        deviceToken))));
    }
}
