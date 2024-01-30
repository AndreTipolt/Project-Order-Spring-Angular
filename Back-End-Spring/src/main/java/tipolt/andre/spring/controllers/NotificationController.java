package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tipolt.andre.spring.dtos.ReadNotificationsDTO;
import tipolt.andre.spring.services.NotificationService;


@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {
    

    @Autowired
    private NotificationService notificationService;


    @PutMapping("/read")
    public ResponseEntity<Void> readNotifications(@RequestBody @Valid ReadNotificationsDTO readNotificationsDTO) {

        notificationService.readNotifications(readNotificationsDTO);
        
        return ResponseEntity.noContent().build();
    }
    
}
