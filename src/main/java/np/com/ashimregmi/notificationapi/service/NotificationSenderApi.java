package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;

public interface NotificationSenderApi {
    void send(SpecificDeviceRmqMessage specificDeviceRmqMessage);
}
