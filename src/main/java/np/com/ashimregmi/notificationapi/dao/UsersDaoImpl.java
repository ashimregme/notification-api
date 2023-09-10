package np.com.ashimregmi.notificationapi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dto.Device;
import np.com.ashimregmi.notificationapi.dto.RequestRmqMessage;
import np.com.ashimregmi.notificationapi.entity.DeviceType;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;
import np.com.ashimregmi.notificationapi.utils.ResultSetMappingUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UsersDaoImpl implements UsersDao {
    private final EntityManager entityManager;

    private static String getFinalCountQueryString(List<String> whereClauses) {
        String baseQueryString = "select count(id) from users u";
        if (whereClauses.isEmpty())
            return baseQueryString;
        return baseQueryString + " where " + String.join(" and ", whereClauses);
    }

    private static String getFinalDeviceTokenQueryString(List<String> whereClauses) {
        String baseQueryString = "select user_device, device_token from users";
        if (whereClauses.isEmpty())
            return baseQueryString;
        return baseQueryString + " where " + String.join(" and ", whereClauses);
    }

    @Override
    public Long getCount(RequestRmqMessage requestRmqMessage) {
        List<String> whereClauses = new ArrayList<>();
        if (!requestRmqMessage.tags().isEmpty()) {
            whereClauses.add("tags @> :tags");
        }
        if (requestRmqMessage.targetOS() == NotificationTargetOS.ANDROID
                || requestRmqMessage.targetOS() == NotificationTargetOS.IOS) {
            whereClauses.add("user_device = :user_device");
        }

        Query query = entityManager.createNativeQuery(
                getFinalCountQueryString(whereClauses),
                Long.class);
        if (!requestRmqMessage.tags().isEmpty()) {
            query.setParameter("tags", requestRmqMessage.tags().toArray(new String[0]));
        }
        if (requestRmqMessage.targetOS() == NotificationTargetOS.ANDROID) {
            query.setParameter("user_device", DeviceType.ANDROID.name());
        } else if (requestRmqMessage.targetOS() == NotificationTargetOS.IOS) {
            query.setParameter("user_device", DeviceType.IOS.name());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Map<Device, List<String>> getDeviceTokensByDevice(List<String> tags,
                                                             NotificationTargetOS targetOS,
                                                             int from,
                                                             int limit) {
        List<String> whereClauses = new ArrayList<>();
        if (!tags.isEmpty()) {
            whereClauses.add("tags @> :tags");
        }
        if (targetOS == NotificationTargetOS.ANDROID || targetOS == NotificationTargetOS.IOS) {
            whereClauses.add("user_device = :user_device");
        }
        Session session = entityManager.unwrap(Session.class);

        TypedQuery<Tuple> query = session.createNativeQuery(
                getFinalDeviceTokenQueryString(whereClauses),
                Tuple.class
        );
        if (!tags.isEmpty()) {
            query.setParameter("tags", tags.toArray(new String[0]));
        }
        if (targetOS == NotificationTargetOS.ANDROID) {
            query.setParameter("user_device", DeviceType.ANDROID.name());
        } else if (targetOS == NotificationTargetOS.IOS) {
            query.setParameter("user_device", DeviceType.IOS.name());
        }
        List<Tuple> tuples = query.getResultList();
        if (tuples.isEmpty()) {
            return Collections.emptyMap();
        }

        List<Map<String, Object>> resultMapList = ResultSetMappingUtils.convertTuplesToMap(tuples);
        Map<Device, List<String>> tokensByDevice = new HashMap<>();
        resultMapList.forEach(resultMap -> {
            Device device = Device.valueOf(String.valueOf(resultMap.get("user_device")));
            List<String> tokens = tokensByDevice.getOrDefault(device, new ArrayList<>());
            tokens.add(String.valueOf(resultMap.get("device_token")));
            tokensByDevice.put(device, tokens);
        });
        return tokensByDevice;
    }

    @Override
    public List<String> getDeviceTokens(List<String> tags, NotificationTargetOS targetOS, int from, int limit) {
        List<String> whereClauses = new ArrayList<>();
        if (!tags.isEmpty()) {
            whereClauses.add("tags @> :tags");
        }
        if (targetOS == NotificationTargetOS.ANDROID || targetOS == NotificationTargetOS.IOS) {
            whereClauses.add("user_device = :user_device");
        }

        String queryString = "select device_token from users";
        if (!whereClauses.isEmpty())
            queryString += " where " + String.join(" and ", whereClauses);
        Query query = entityManager.createNativeQuery(queryString, String.class);
        if (!tags.isEmpty()) {
            query.setParameter("tags", tags.toArray(new String[0]));
        }
        if (targetOS == NotificationTargetOS.ANDROID) {
            query.setParameter("user_device", DeviceType.ANDROID.name());
        } else if (targetOS == NotificationTargetOS.IOS) {
            query.setParameter("user_device", DeviceType.IOS.name());
        }
        return query.getResultList();
    }
}
