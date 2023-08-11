package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.request.SendNotificationRequest;

public interface NotificationApi {
    void send(SendNotificationRequest sendNotificationRequest);
}
