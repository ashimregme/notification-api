package np.com.ashimregmi.notificationapi.dto;

import np.com.ashimregmi.notificationapi.request.NotificationPayload;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;

import java.util.List;

public record BatchedRmqMessage(
        String batchId,
        NotificationTargetOS targetOS,
        List<String> tags,
        NotificationPayload payload,
        int from,
        int limit
) {
}
