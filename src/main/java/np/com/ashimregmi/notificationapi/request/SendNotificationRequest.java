package np.com.ashimregmi.notificationapi.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SendNotificationRequest(@NotNull(message = "targetOS is required") NotificationTargetOS targetOS,
        @NotEmpty(message = "at least one tag is required") List<String> tags,
        @NotNull(message = "payload is required") NotificationPayload payload) {
}
