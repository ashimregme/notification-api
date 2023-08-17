package np.com.ashimregmi.notificationapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import np.com.ashimregmi.notificationapi.dto.QueuedMessage;
import np.com.ashimregmi.notificationapi.request.NotificationPayload;
import np.com.ashimregmi.notificationapi.request.NotificationTargetOS;

@SpringBootTest
class RmqNotificationQueueingServiceTest {
    @MockBean
    private RmqApi rmqApi;

    @Captor
    ArgumentCaptor<String> bodyCaptor;

    @Test
    @SuppressWarnings("unchecked")
    void testQueue() throws JsonProcessingException {
        var payload = new NotificationPayload(
                "http://example.com/icon.jpg",
                "http://example.com/banner.jpg",
                "Title 1",
                "Short Description 1",
                "Long Description 1");
        var queuedMessage = new QueuedMessage(
                NotificationTargetOS.ANDROID,
                Collections.emptyList(),
                payload);
        RmqNotificationQueueingService rmqNotificationQueueingService = new RmqNotificationQueueingService(
                rmqApi,
                "",
                "");
        rmqNotificationQueueingService.queue(queuedMessage);

        verify(rmqApi, times(1))
                .send(anyString(), anyString(), bodyCaptor.capture());

        String body = bodyCaptor.getValue();

        Map<String, Object> bodyMap = new ObjectMapper().readerFor(Map.class).readValue(body);

        assertEquals("ANDROID", bodyMap.get("targetOS"));

        assertTrue(bodyMap.get("tags") instanceof ArrayList);
        List<String> tags = (ArrayList<String>) bodyMap.get("tags");
        assertNotNull(tags);
        assertTrue(tags.isEmpty());

        assertNotNull(bodyMap.get("payload"));
        assertTrue(bodyMap.get("payload") instanceof LinkedHashMap);
        Map<String, Object> payloadMap = (Map<String, Object>) bodyMap.get("payload");

        assertEquals("http://example.com/icon.jpg", payloadMap.get("iconUrl"));
        assertEquals("http://example.com/banner.jpg", payloadMap.get("bannerUrl"));
        assertEquals("Title 1", payloadMap.get("title"));
        assertEquals("Short Description 1", payloadMap.get("shortDescription"));
        assertEquals("Long Description 1", payloadMap.get("longDescription"));
    }
}
