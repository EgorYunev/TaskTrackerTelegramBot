package org.sharpbubbels.TaskTrackerBot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class UserNotifications {

    @Id
    @GeneratedValue
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime notification;

    @ManyToOne
    private AppUser user;

}
