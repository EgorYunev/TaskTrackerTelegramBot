package org.sharpbubbels.TaskTrackerBot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tg_user")
public class AppUser {

    private Long userChatId;

    @Id
    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserNotifications> userNotifications;

}