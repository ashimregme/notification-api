package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.BatchedRmqMessage;

public interface BatchProcessor {
    void process(BatchedRmqMessage batchedRmqMessage);
}
