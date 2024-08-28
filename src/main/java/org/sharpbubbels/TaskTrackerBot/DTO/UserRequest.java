package org.sharpbubbels.TaskTrackerBot.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class UserRequest {

    String username;

    List<String> dataTimeOfTasks;

}
