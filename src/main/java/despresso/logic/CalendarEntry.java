package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.LocalDateTime;


@UIScope
@SpringComponent
public class CalendarEntry {
    private String _id;
    private LocalDateTime _startTime;
    private LocalDateTime _endTime;
    private String _title;
    private String _description;
    private String _color;
    private boolean _isDone;

    public CalendarEntry(String id, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color, boolean isDone){
        _id = id;
        _startTime = startTime;
        _endTime = endTime;
        _title = title;
        _description = description;
        _color = color;
        _isDone = isDone;
    }

    public String getId(){
        return _id;
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

    public boolean getIsDone(){
        return _isDone;
    }

    public void setId(String id)
    {
        _id = id;
    }

    public void setStart (LocalDateTime start){
        _startTime = start;
    }

    public void setEnd(LocalDateTime end){
        _endTime = end;
    }

    public void setTitle(String title){
        _title = title;
    }

    public void setDescription(String description){
        _description = description;
    }

    public void setColor(String color){
        _color = color;
    }

    public void setIsDone(boolean isDone){
        _isDone = isDone;
    }
}
