package np.com.ashimregmi.notificationapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SendNotificationRequest(
        @NotNull(message = "targetOS is required")
        @Schema(description = "OS to send notification", requiredMode = Schema.RequiredMode.REQUIRED)
        NotificationTargetOS targetOS,
        @NotEmpty(message = "at least one tag is required")
        @Schema(description = "tagged users to target", requiredMode = Schema.RequiredMode.REQUIRED)
        List<String> tags,
        @NotNull(message = "payload is required")
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        NotificationPayload payload) {
}
