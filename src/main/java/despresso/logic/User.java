package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.LocalDate;

@UIScope
@SpringComponent
public class User implements DataTypeInterface {

    private int id;
    private String fname, lname;
    private LocalDate dob;

    public User(int id, String fname, String lname, LocalDate dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.id = id;
    }

    public User(String fname, String lname, LocalDate dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public User(){

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "User: " + this.id + ", " + this.fname + ", " + this.lname + ", " + this.dob;
    }
}
