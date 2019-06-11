package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.LocalDateTime;


@UIScope
@SpringComponent
public class CalendarEntry {
    private int id;
    private String _userId;
    private LocalDateTime _startTime;
    private LocalDateTime _endTime;
    private String _title;
    private String _description;
    private String _color;
    private boolean _isDone;

    public CalendarEntry(String userId, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color, boolean isDone){
        _userId = userId;
        _startTime = startTime;
        _endTime = endTime;
        _title = title;
        _description = description;
        _color = color;
        _isDone = isDone;
    }

    public CalendarEntry(){}

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

    public boolean getIsDone(){
        return _isDone;
    }

    public void setUserId(String userId)
    {
        _userId = userId;
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

    public String get_userId() {
        return _userId;
    }

    public void set_userId(String _userId) {
        this._userId = _userId;
    }

    public LocalDateTime get_startTime() {
        return _startTime;
    }

    public void set_startTime(LocalDateTime _startTime) {
        this._startTime = _startTime;
    }

    public LocalDateTime get_endTime() {
        return _endTime;
    }

    public void set_endTime(LocalDateTime _endTime) {
        this._endTime = _endTime;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public boolean is_isDone() {
        return _isDone;
    }

    public void set_isDone(boolean _isDone) {
        this._isDone = _isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
