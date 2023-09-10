package np.com.ashimregmi.notificationapi.dao;

import np.com.ashimregmi.notificationapi.dto.Device;
import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;

import java.util.List;
import java.util.Map;

public interface UsersDao {
    Long getCount(RequestRmqMessage requestRmqMessage);

    Map<Device, List<String>> getDeviceTokensByDevice(List<String> tags, NotificationTargetOS targetOS, int from, int limit);

    List<String> getDeviceTokens(List<String> tags, NotificationTargetOS targetOS, int from, int limit);
}
