package np.com.ashimregmi.notificationapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import np.com.ashimregmi.notificationapi.request.SendNotificationRequest;
import np.com.ashimregmi.notificationapi.service.NotificationApi;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationApi notificationService;

    @PostMapping("/notification")
    public ResponseEntity<Object> send(@RequestBody SendNotificationRequest sendNotificationRequest) {
        notificationService.send(sendNotificationRequest);
        return ResponseEntity.ok().build();
    }
}
