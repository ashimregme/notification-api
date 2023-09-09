package np.com.ashimregmi.notificationapi.exception;

public class NotificationSendingException extends RuntimeException {
    public NotificationSendingException(String message) {
        super(message);
    }

    public NotificationSendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificationSendingException(Throwable cause) {
        super(cause);
    }
}
