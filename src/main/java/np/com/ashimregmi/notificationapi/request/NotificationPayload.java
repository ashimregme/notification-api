package np.com.ashimregmi.notificationapi.request;

import jakarta.validation.constraints.NotBlank;

public record NotificationPayload(
                @NotBlank(message = "iconUrl is required") String iconUrl,
                @NotBlank(message = "bannerUrl is required") String bannerUrl,
                @NotBlank(message = "title is required") String title,
                @NotBlank(message = "shortDescription is required") String shortDescription,
                @NotBlank(message = "longDescription is required") String longDescription) {
}
