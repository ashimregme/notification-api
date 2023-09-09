package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.dto.BatchedRmqMessage;
import np.com.ashimregmi.notificationapi.dto.Device;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class BatchProcessorImpl implements BatchProcessor {
    private final UsersDao usersDao;
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;

    @Override
    public void process(BatchedRmqMessage batchedRmqMessage) {
        Map<Device, List<String>> tokensByDevice = usersDao.getDeviceTokens(
                batchedRmqMessage.tags(),
                batchedRmqMessage.targetOS(),
                batchedRmqMessage.from(),
                batchedRmqMessage.limit()
        );

        tokensByDevice.forEach((device, deviceTokens) -> {
            deviceTokens.forEach(deviceToken -> rmqApi.send(
                    exchangeName,
                    routingKey,
                    JsonUtils.toJson(new SpecificDeviceRmqMessage(
                            device,
                            deviceToken,
                            batchedRmqMessage.payload()
                    ))
            ));
        });
    }
}
