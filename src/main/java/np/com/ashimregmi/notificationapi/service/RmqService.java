package np.com.ashimregmi.notificationapi.service;

public interface RmqService {
    void send(String exchangeName, String routingKey, String body);
}
