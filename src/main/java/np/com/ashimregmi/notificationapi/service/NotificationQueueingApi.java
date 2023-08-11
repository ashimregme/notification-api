package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.QueuedMessage;

public interface NotificationQueueingApi {
    void queue(QueuedMessage queuedMessage);
}
