package np.com.ashimregmi.notificationapi.dto;

import np.com.ashimregmi.notificationapi.request.NotificationPayload;

import java.util.List;

public record SpecificDeviceRmqMessage(List<String> tokens, NotificationPayload payload) {
}
