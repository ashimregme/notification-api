package np.com.ashimregmi.notificationapi.service;

public interface RmqApi {
    void send(String exchangeName, String routingKey, String body);
}
