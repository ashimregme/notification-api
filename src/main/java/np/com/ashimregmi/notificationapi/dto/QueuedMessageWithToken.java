package np.com.ashimregmi.notificationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueuedMessageWithToken {
    private QueuedMessage queuedMessage;
    private String deviceToken;
}
