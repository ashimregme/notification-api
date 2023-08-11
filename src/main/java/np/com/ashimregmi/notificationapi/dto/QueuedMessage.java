package np.com.ashimregmi.notificationapi.dto;

import java.util.List;

import np.com.ashimregmi.notificationapi.request.NotificationPayload;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;

public record QueuedMessage(NotificationTargetOS targetOS, List<String> tags, NotificationPayload payload) {

}
