package np.com.ashimregmi.notificationapi.dto;

import np.com.ashimregmi.notificationapi.request.NotificationPayload;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;

import java.util.List;

public record RequestRmqMessage(NotificationTargetOS targetOS, List<String> tags, NotificationPayload payload) {

}
