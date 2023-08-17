package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

public class RmqNotificationQueueingService implements NotificationQueueingApi {
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;

    public RmqNotificationQueueingService(RmqApi rmqApi, String exchangeName, String routingKey) {
        this.rmqApi = rmqApi;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    @Override
    public void queue(QueuedMessage queuedMessage) {
        rmqApi.send(exchangeName, routingKey, JsonUtils.toJson(queuedMessage));
    }
}
