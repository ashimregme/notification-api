package np.com.ashimregmi.notificationapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record NotificationPayload(
        @NotBlank(message = "iconUrl is required")
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String iconUrl,
        @NotBlank(message = "bannerUrl is required")
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String bannerUrl,
        @NotBlank(message = "title is required")
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String title,
        @NotBlank(message = "shortDescription is required")
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String shortDescription,
        @NotBlank(message = "longDescription is required")
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String longDescription) {
}
