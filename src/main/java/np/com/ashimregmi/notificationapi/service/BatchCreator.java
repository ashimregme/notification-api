package np.com.ashimregmi.notificationapi.service;

import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;

public interface BatchCreator {
    void createBatchesAndSend(RequestRmqMessage requestRmqMessage);
}
