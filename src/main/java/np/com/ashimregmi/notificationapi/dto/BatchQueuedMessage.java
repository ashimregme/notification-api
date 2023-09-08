package np.com.ashimregmi.notificationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchQueuedMessage {
    private QueuedMessage queuedMessage;
    private int from;
    private int limit;
}
