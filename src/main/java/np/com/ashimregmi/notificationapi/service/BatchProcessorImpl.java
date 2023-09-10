package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.dto.BatchedRmqMessage;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;
import np.com.ashimregmi.notificationapi.utils.ListUtils;

import java.util.List;

@RequiredArgsConstructor
public class BatchProcessorImpl implements BatchProcessor {
    private final UsersDao usersDao;
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;

    @Override
    public void process(BatchedRmqMessage batchedRmqMessage) {
        List<String> deviceTokens = usersDao.getDeviceTokens(
                batchedRmqMessage.tags(),
                batchedRmqMessage.targetOS(),
                batchedRmqMessage.from(),
                batchedRmqMessage.limit()
        );

        ListUtils.batches(deviceTokens, 1000).forEach(tokens -> rmqApi.send(
                exchangeName,
                routingKey,
                JsonUtils.toJson(new SpecificDeviceRmqMessage(
                        tokens,
                        batchedRmqMessage.payload()
                ))
        ));
    }
}
