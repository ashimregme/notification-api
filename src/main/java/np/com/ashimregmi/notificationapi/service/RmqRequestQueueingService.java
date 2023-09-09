package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

@Slf4j
@RequiredArgsConstructor
public class RmqRequestQueueingService implements RequestQueueingApi {
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;

    @Override
    public void queue(RequestRmqMessage requestRmqMessage) {
        log.debug("queuedMessage: {}", requestRmqMessage);
        rmqApi.send(exchangeName, routingKey, JsonUtils.toJson(requestRmqMessage));
    }
}
