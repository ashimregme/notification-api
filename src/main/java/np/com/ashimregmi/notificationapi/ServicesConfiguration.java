package np.com.ashimregmi.notificationapi;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import np.com.ashimregmi.notificationapi.service.NotificationApi;
import np.com.ashimregmi.notificationapi.service.NotificationQueueingApi;
import np.com.ashimregmi.notificationapi.service.RmqNotificationQueueingService;
import np.com.ashimregmi.notificationapi.service.NotificationService;
import np.com.ashimregmi.notificationapi.service.RmqApi;
import np.com.ashimregmi.notificationapi.service.RmqService;

@Configuration
public class ServicesConfiguration {
    @Bean
    NotificationApi notificationApi(NotificationQueueingApi notificationQueueingApi) {
        return new NotificationService(notificationQueueingApi);
    }

    @Bean
    NotificationQueueingApi notificationQueueingApi(RmqApi rmqApi,
            @Value("${notification.exchange.name}") String exchangeName,
            @Value("${notification.routing.key}") String routingKey) {
        return new RmqNotificationQueueingService(rmqApi, exchangeName, routingKey);
    }

    @Bean
    RmqApi rmqApi(RabbitTemplate rabbitTemplate) {
        return new RmqService(rabbitTemplate);
    }
}
