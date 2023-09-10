package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;
import np.com.ashimregmi.notificationapi.request.SendNotificationRequest;

@Slf4j
@RequiredArgsConstructor
public class NotificationService implements NotificationApi {
    private final RequestQueueingApi requestQueueingApi;

    @Override
    public void send(SendNotificationRequest sendNotificationRequest) {
        log.debug("sendNotificationRequest: {}", sendNotificationRequest);
        RequestRmqMessage requestRmqMessage = new RequestRmqMessage(
                sendNotificationRequest.targetOS(),
                sendNotificationRequest.tags(),
                sendNotificationRequest.payload()
        );
        requestQueueingApi.queue(requestRmqMessage);
    }
}
