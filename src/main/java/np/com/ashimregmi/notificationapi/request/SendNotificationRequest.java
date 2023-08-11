package np.com.ashimregmi.notificationapi.request;

import java.util.List;

public record SendNotificationRequest(NotificationTargetOS targetOS, List<String> tags, NotificationPayload payload) {
}
