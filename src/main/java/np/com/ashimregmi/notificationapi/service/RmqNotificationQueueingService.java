package np.com.ashimregmi.notificationapi.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

@Slf4j
@RequiredArgsConstructor
public class RmqNotificationQueueingService implements NotificationQueueingApi {
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;

    @Override
    public void queue(QueuedMessage queuedMessage) {
        log.debug("queuedMessage: {}", queuedMessage);
        rmqApi.send(exchangeName, routingKey, JsonUtils.toJson(queuedMessage));
    }
}
