package np.com.ashimregmi.notificationapi.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    private Long id;
    private String name;
    @Column(name = "user_device")
    private String deviceType;
    @Column(name = "device_token")
    private String deviceToken;
    @Type(ListArrayType.class)
    @Column(name = "tags", columnDefinition = "text[]")
    private List<String> tags;
    @Column(name = "created_at")
    private Date createdAt;
}
