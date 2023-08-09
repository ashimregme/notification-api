package np.com.ashimregmi.notificationapi.request;

public record NotificationPayload(
        String iconUrl,
        String bannerUrl,
        String title,
        String shortDescription,
        String longDescription) {
}
