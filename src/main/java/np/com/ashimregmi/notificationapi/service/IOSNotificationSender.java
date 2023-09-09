package np.com.ashimregmi.notificationapi.service;

import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;

@Slf4j
public class IOSNotificationSender implements NotificationSenderApi {
    @Override
    public void send(SpecificDeviceRmqMessage specificDeviceRmqMessage) {
        log.debug("Sending iOS notification: {}", specificDeviceRmqMessage);
    }
}
