package np.com.ashimregmi.notificationapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.request.SendNotificationRequest;
import np.com.ashimregmi.notificationapi.service.NotificationApi;

@RestController
@RequiredArgsConstructor
public class NotificationController extends BaseController {
    private final NotificationApi notificationApi;

    @PostMapping("/notification")
    public ResponseEntity<Object> send(@Valid @RequestBody SendNotificationRequest sendNotificationRequest) {
        notificationApi.send(sendNotificationRequest);
        return ResponseEntity.ok().build();
    }
}
