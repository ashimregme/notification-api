package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.QueuedMessage;

public interface BatchCreator {
    void createBatchesAndSend(QueuedMessage queuedMessage);
}
