package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;

@Slf4j
@RequiredArgsConstructor
public class SendNotificationService implements SendNotificationApi {
    private final NotificationSenderApi androidNotificationSender;
    @Override
    public void send(SpecificDeviceRmqMessage specificDeviceRmqMessage) {
        androidNotificationSender.send(specificDeviceRmqMessage);
    }
}
