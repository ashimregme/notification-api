package np.com.ashimregmi.notificationapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import np.com.ashimregmi.notificationapi.request.NotificationRequest;

@RestController
public class NotificationController {
    @PostMapping("/notification")
    public ResponseEntity<Object> send(@RequestBody NotificationRequest notificationRequest) {
        return ResponseEntity.ok().build();
    }
}
