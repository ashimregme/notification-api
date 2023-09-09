package np.com.ashimregmi.notificationapi.service;

import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;
import np.com.ashimregmi.notificationapi.exception.NotificationSendingException;

@Slf4j
public class NotificationSenderImpl implements NotificationSenderApi {

    @Override
    public void send(SpecificDeviceRmqMessage specificDeviceRmqMessage) {
        log.debug("Sending android notification: {}", specificDeviceRmqMessage);

        Message message = Message.builder()

                .setToken(specificDeviceRmqMessage.token())

                .setNotification(
                        Notification.builder()
                                .setTitle(specificDeviceRmqMessage.payload().title())
                                .setBody(specificDeviceRmqMessage.payload().shortDescription())
                                .setImage(specificDeviceRmqMessage.payload().bannerUrl())
                                .build()
                )

                .setAndroidConfig(
                        AndroidConfig.builder()
                                .setTtl(60 * 60 * 1000)
                                .setNotification(
                                        AndroidNotification.builder()
                                                .setIcon(specificDeviceRmqMessage.payload().iconUrl())
                                                .build()
                                )
                                .build()
                )

                .setApnsConfig(
                        ApnsConfig.builder()
                                .setAps(
                                        Aps.builder()
                                                .build()
                                )
                                .build()
                )

                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            throw new NotificationSendingException(e);
        }
        log.debug("Android notification sent, response: {}", response);
    }
}
