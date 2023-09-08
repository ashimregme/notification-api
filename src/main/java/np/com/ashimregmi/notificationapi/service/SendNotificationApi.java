package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.QueuedMessageWithToken;

public interface SendNotificationApi {
    void send(QueuedMessageWithToken queuedMessageWithToken);
}
