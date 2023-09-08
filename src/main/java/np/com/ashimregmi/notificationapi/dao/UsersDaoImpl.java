package np.com.ashimregmi.notificationapi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.entity.Users;
import np.com.ashimregmi.notificationapi.entity.Users_;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsersDaoImpl implements UsersDao {
    private final EntityManager entityManager;

    @Override
    public Long getCount(QueuedMessage queuedMessage) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Users> usersRoot = cq.from(Users.class);
        List<Predicate> predicates = getPredicates(queuedMessage, cb, usersRoot);
        cq.select(cb.count(usersRoot.get(Users_.ID))).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<String> getDeviceTokens(QueuedMessage queuedMessage, int from, int limit) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);

        Root<Users> usersRoot = cq.from(Users.class);
        List<Predicate> predicates = getPredicates(queuedMessage, cb, usersRoot);
        cq.select(usersRoot.get(Users_.deviceToken)).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    private static List<Predicate> getPredicates(QueuedMessage queuedMessage,
                                                 CriteriaBuilder cb,
                                                 Root<Users> usersRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (queuedMessage.targetOS() == NotificationTargetOS.ANDROID) {
            predicates.add(
                    cb.equal(usersRoot.get(Users_.userDevice), NotificationTargetOS.ANDROID.name())
            );
        } else if (queuedMessage.targetOS() == NotificationTargetOS.IOS) {
            predicates.add(
                    cb.equal(usersRoot.get(Users_.userDevice), NotificationTargetOS.IOS.name())
            );
        }

        if (!queuedMessage.tags().isEmpty()) {
            predicates.add(usersRoot.get(Users_.tags).in(queuedMessage.tags()));
        }
        return predicates;
    }
}
