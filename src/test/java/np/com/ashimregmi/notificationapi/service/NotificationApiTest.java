package np.com.ashimregmi.notificationapi.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.request.NotificationPayload;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;
import np.com.ashimregmi.notificationapi.request.SendNotificationRequest;

@SpringBootTest
class NotificationApiTest {
    @MockBean
    private NotificationQueueingApi notificationQueueingApi;

    @Autowired
    private NotificationApi notificationApi;

    @Test
    void testSend() {
        var sendNotificationRequest = new SendNotificationRequest(
                NotificationTargetOS.ANDROID,
                Collections.emptyList(),
                new NotificationPayload(
                        "http://image.com/icon.png",
                        "http://image.com/banner.png",
                        "Title 1",
                        "Short Description 1",
                        "Long Description 2"));
        notificationApi.send(sendNotificationRequest);
        QueuedMessage queuedMessage = new QueuedMessage(
                sendNotificationRequest.targetOS(),
                sendNotificationRequest.tags(),
                sendNotificationRequest.payload());
        verify(notificationQueueingApi, times(1)).queue(queuedMessage);
    }
}
