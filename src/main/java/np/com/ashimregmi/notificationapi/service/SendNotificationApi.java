package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;

public interface SendNotificationApi {
    void send(SpecificDeviceRmqMessage specificDeviceRmqMessage);
}
