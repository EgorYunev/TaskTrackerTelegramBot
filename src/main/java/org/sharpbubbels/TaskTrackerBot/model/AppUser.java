package org.sharpbubbels.TaskTrackerBot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Data
@Setter
@Getter
public class AppUser {

    private Long userChatId;
    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateTimeOfTask;

    public void setDateTimeOfTask(String dateTimeOfTaskStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeOfTask = LocalDateTime.parse(dateTimeOfTaskStr, formatter);

        this.dateTimeOfTask = dateTimeOfTask;
    }
}