package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.dto.BatchQueuedMessage;
import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

@RequiredArgsConstructor
public class BatchCreatorImpl implements BatchCreator {
    private final UsersDao usersDao;
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;
    private final int batchSize;

    @Override
    public void createBatchesAndSend(QueuedMessage queuedMessage) {
        Long count = usersDao.getCount(queuedMessage);

        for (int from = 0; from < count; from += batchSize) {
            rmqApi.send(
                    exchangeName,
                    routingKey,
                    JsonUtils.toJson(new BatchQueuedMessage(queuedMessage, from, batchSize)));
        }
    }
}
