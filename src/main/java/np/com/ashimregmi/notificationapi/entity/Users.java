package np.com.ashimregmi.notificationapi.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
@Data
public class Users {
    @Id
    private Long id;
    private String name;
    @Column(name = "user_device")
    private String userDevice;
    @Column(name = "device_token")
    private String deviceToken;
    @Column(columnDefinition = "text[]")
    @Type(StringArrayType.class)
    private String[] tags;
    @Column(name = "created_at")
    private Date createdAt;
}
