package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.request.NotificationRequest;

public interface NotificationService {
    public void send(NotificationRequest notificationRequest);
}
