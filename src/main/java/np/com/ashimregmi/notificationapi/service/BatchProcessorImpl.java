package np.com.ashimregmi.notificationapi.service;

import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dao.UsersDao;
import np.com.ashimregmi.notificationapi.dto.BatchedRmqMessage;
import np.com.ashimregmi.notificationapi.dto.SpecificDeviceRmqMessage;
import np.com.ashimregmi.notificationapi.utils.JsonUtils;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class BatchProcessorImpl implements BatchProcessor {
    private final UsersDao usersDao;
    private final RmqApi rmqApi;
    private final String exchangeName;
    private final String routingKey;

    public static <T> Stream<List<T>> batches(List<T> source, int length) {
        if (length <= 0)
            throw new IllegalArgumentException("length = " + length);
        int size = source.size();
        if (size <= 0)
            return Stream.empty();
        int fullChunks = (size - 1) / length;
        return IntStream.range(0, fullChunks + 1).mapToObj(
                n -> source.subList(n * length, n == fullChunks ? size : (n + 1) * length));
    }

    @Override
    public void process(BatchedRmqMessage batchedRmqMessage) {
        List<String> deviceTokens = usersDao.getDeviceTokens(
                batchedRmqMessage.tags(),
                batchedRmqMessage.targetOS(),
                batchedRmqMessage.from(),
                batchedRmqMessage.limit()
        );

        batches(deviceTokens, 1000).forEach(tokens -> rmqApi.send(
                exchangeName,
                routingKey,
                JsonUtils.toJson(new SpecificDeviceRmqMessage(
                        tokens,
                        batchedRmqMessage.payload()
                ))
        ));
    }
}
