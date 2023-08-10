package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.MessageToQueue;

public interface NotificationQueueingService {
    void queue(MessageToQueue messageToQueue);
}
