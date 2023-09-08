package np.com.ashimregmi.notificationapi;

import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.service.*;
import np.com.ashimregmi.notificationapi.service.consumer.RmqBatchConsumer;
import np.com.ashimregmi.notificationapi.service.consumer.RmqNotificationRequestConsumer;
import np.com.ashimregmi.notificationapi.service.consumer.RmqSendConsumer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    BatchCreator batchCreator(UsersDao usersDao,
                              RmqApi rmqApi,
                              @Value("${batch.exchange.name}") String exchangeName,
                              @Value("${batch.routing.key}") String routingKey,
                              @Value("${batch.size}") int batchSize) {
        return new BatchCreatorImpl(usersDao, rmqApi, exchangeName, routingKey, batchSize);
    }

    @Bean
    SendNotificationApi sendNotificationApi() {
        return new SendNotificationService();
    }

    @Bean
    RmqNotificationRequestConsumer rmqNotificationRequestConsumer(BatchCreator batchCreator) {
        return new RmqNotificationRequestConsumer(batchCreator);
    }

    @Bean
    RmqBatchConsumer rmqBatchConsumer(UsersDao usersDao,
                                      RmqApi rmqApi,
                                      @Value("${send.exchange.name}") String exchangeName,
                                      @Value("${send.routing.key}") String routingKey) {
        return new RmqBatchConsumer(usersDao, rmqApi, exchangeName, routingKey);
    }

    @Bean
    RmqSendConsumer rmqSendConsumer(SendNotificationApi sendNotificationApi) {
        return new RmqSendConsumer(sendNotificationApi);
    }
}
