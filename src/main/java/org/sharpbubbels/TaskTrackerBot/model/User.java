package org.sharpbubbels.TaskTrackerBot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class User {
    String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateTimeOfTask;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateTimeOfTask(String dateTimeOfTaskStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeOfTask = LocalDateTime.parse(dateTimeOfTaskStr, formatter);

        this.dateTimeOfTask = dateTimeOfTask;
    }

    public String getUsername() {
        return username;
    }

}