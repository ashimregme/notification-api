package np.com.ashimregmi.notificationapi.configuration;

import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.service.*;
import np.com.ashimregmi.notificationapi.service.consumer.RmqBatchConsumer;
import np.com.ashimregmi.notificationapi.service.consumer.RmqRequestConsumer;
import np.com.ashimregmi.notificationapi.service.consumer.RmqSendConsumer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {
    @Bean
    NotificationApi notificationApi(RequestQueueingApi requestQueueingApi) {
        return new NotificationService(requestQueueingApi);
    }

    @Bean
    RequestQueueingApi notificationQueueingApi(RmqApi rmqApi,
                                               @Value("${notification.exchange.name}") String exchangeName,
                                               @Value("${notification.routing.key}") String routingKey) {
        return new RmqRequestQueueingService(rmqApi, exchangeName, routingKey);
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
    NotificationSenderApi androidNotificationSender() {
        return new NotificationSenderImpl();
    }

    @Bean
    SendNotificationApi sendNotificationApi(NotificationSenderApi androidNotificationSender) {
        return new SendNotificationService(androidNotificationSender);
    }

    @Bean
    RmqRequestConsumer rmqNotificationRequestConsumer(BatchCreator batchCreator) {
        return new RmqRequestConsumer(batchCreator);
    }

    @Bean
    BatchProcessor batchProcessor(UsersDao usersDao,
                                      RmqApi rmqApi,
                                      @Value("${send.exchange.name}") String exchangeName,
                                      @Value("${send.routing.key}") String routingKey) {
        return new BatchProcessorImpl(usersDao, rmqApi, exchangeName, routingKey);
    }

    @Bean
    RmqBatchConsumer rmqBatchConsumer(BatchProcessor batchProcessor) {
        return new RmqBatchConsumer(batchProcessor);
    }

    @Bean
    RmqSendConsumer rmqSendConsumer(SendNotificationApi sendNotificationApi) {
        return new RmqSendConsumer(sendNotificationApi);
    }
}
