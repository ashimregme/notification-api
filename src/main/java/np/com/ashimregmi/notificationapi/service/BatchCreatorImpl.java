package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.dto.BatchedRmqMessage;
import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

import java.util.UUID;

@RequiredArgsConstructor
public class BatchCreatorImpl implements BatchCreator {
    private final UsersDao usersDao;
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;
    private final int batchSize;

    @Override
    public void createBatchesAndSend(RequestRmqMessage requestRmqMessage) {
        Long count = usersDao.getCount(requestRmqMessage);

        for (int from = 0; from < count; from += batchSize) {
            rmqApi.send(
                    exchangeName,
                    routingKey,
                    JsonUtils.toJson(new BatchedRmqMessage(
                            UUID.randomUUID().toString(),
                            requestRmqMessage.targetOS(),
                            requestRmqMessage.tags(),
                            requestRmqMessage.payload(),
                            from,
                            batchSize
                    ))
            );
        }
    }
}
