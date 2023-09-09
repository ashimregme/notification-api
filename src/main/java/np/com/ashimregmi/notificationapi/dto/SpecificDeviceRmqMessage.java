package np.com.ashimregmi.notificationapi.dto;

import np.com.ashimregmi.notificationapi.request.NotificationPayload;

public record SpecificDeviceRmqMessage(Device device, String token, NotificationPayload payload) {
}
