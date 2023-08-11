package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.request.SendNotificationRequest;

@RequiredArgsConstructor
public class NotificationService implements NotificationApi {
    private final NotificationQueueingApi notificationQueueingApi;

    @Override
    public void send(SendNotificationRequest sendNotificationRequest) {
        QueuedMessage queuedMessage = new QueuedMessage(
                sendNotificationRequest.targetOS(),
                sendNotificationRequest.tags(),
                sendNotificationRequest.payload());
        notificationQueueingApi.queue(queuedMessage);
    }
}
