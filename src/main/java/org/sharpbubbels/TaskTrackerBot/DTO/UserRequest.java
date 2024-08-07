package org.sharpbubbels.TaskTrackerBot.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserRequest {

    String username;

    String dateTimeOfTask;

}
