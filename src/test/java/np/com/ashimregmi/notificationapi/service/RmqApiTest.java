package np.com.ashimregmi.notificationapi.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RmqApiTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RmqApi rmqApi;

    @Test
    void testSend() {
        String exchangeName = "exchange-name";
        String routeName = "route-name";
        rmqApi.send(exchangeName, routeName, "{}");

        verify(rabbitTemplate).convertAndSend(exchangeName, routeName, "{}");
    }
}
