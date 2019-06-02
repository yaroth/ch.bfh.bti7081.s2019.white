package despresso.logic;

import java.time.LocalDateTime;

public class CalendarEntry {
    private String _userId;
    private LocalDateTime _startTime;
    private LocalDateTime _endTime;
    private String _title;
    private String _description;
    private String _color;

    public CalendarEntry(String userId, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color){
        _userId = userId;
        _startTime = startTime;
        _endTime = endTime;
        _title = title;
        _description = description;
        _color = color;
    }

    public String getUserId(){
        return _userId;
    }

    public LocalDateTime getStart(){
        return _startTime;
    }

    public LocalDateTime getEnd(){
        return _endTime;
    }

    public String getTitle(){
        return _title;
    }

    public String getDescription() {
        return _description;
    }

    public String getColor(){
        return _color;
    }
}
