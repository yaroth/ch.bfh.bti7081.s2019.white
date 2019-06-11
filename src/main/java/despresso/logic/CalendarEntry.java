package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.LocalDateTime;


@UIScope
@SpringComponent
public class CalendarEntry {
    private int _id;
    private String _entryId;
    private LocalDateTime _startTime;
    private LocalDateTime _endTime;
    private String _title;
    private String _description;
    private String _color;
    private boolean _isDone;

    public CalendarEntry(int id, String entryId, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color, boolean isDone){
        _id = id;
        _entryId = entryId;
        _startTime = startTime;
        _endTime = endTime;
        _title = title;
        _description = description;
        _color = color;
        _isDone = isDone;
    }

    public CalendarEntry(){
        //constructor for repository
    }

    public int getId(){
        return _id;
    }

    public String getEntryId() { return _entryId; }

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

    public void setId(int id)
    {
        _id = id;
    }

    public void setEntryId(String entryId){
        _entryId = entryId;
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
