package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;
import np.com.ashimregmi.notificationapi.request.NotificationPayload;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;
import np.com.ashimregmi.notificationapi.request.SendNotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class NotificationApiTest {
    @MockBean
    private RequestQueueingApi requestQueueingApi;

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
        RequestRmqMessage requestRmqMessage = new RequestRmqMessage(
                sendNotificationRequest.targetOS(),
                sendNotificationRequest.tags(),
                sendNotificationRequest.payload());
        verify(requestQueueingApi, times(1)).queue(requestRmqMessage);
    }
}
