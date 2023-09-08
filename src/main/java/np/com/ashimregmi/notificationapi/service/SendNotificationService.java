package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.QueuedMessageWithToken;

@Slf4j
@RequiredArgsConstructor
public class SendNotificationService implements SendNotificationApi {
    @Override
    public void send(QueuedMessageWithToken queuedMessageWithToken) {
        log.debug("Notification sent: {}", queuedMessageWithToken);
    }
}
