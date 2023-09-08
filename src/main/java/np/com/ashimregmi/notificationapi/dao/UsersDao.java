package np.com.ashimregmi.notificationapi.dao;

import java.util.List;

import np.com.ashimregmi.notificationapi.dto.QueuedMessage;

public interface UsersDao {
    Long getCount(QueuedMessage queuedMessage);

    List<String> getDeviceTokens(QueuedMessage queuedMessage, int from, int limit);
}
