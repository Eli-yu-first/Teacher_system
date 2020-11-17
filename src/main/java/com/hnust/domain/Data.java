package com.hnust.domain;

import com.sun.deploy.cache.BaseLocalApplicationProperties;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Data {
    private SimpleStringProperty Subject=new SimpleStringProperty();
    private SimpleStringProperty Person=new SimpleStringProperty();
    private SimpleStringProperty Time=new SimpleStringProperty();
    private SimpleBooleanProperty checked=new SimpleBooleanProperty();
    public Data(String Subject,String Person,String Time){
        this.Subject.set(Subject);
        this.Person.set(Person);
        this.Time.set(Time);
        this.checked.set(false);
    }

    public String getSubject(){
        return Subject.get();
    }

    public SimpleStringProperty SubjectProperty(){return Subject;}

    public void setSubject(String Subject){this.Subject.set(Subject);}

    public String getPerson(){
        return Person.get();
    }

    public SimpleStringProperty PersonProperty(){return Person;}

    public void setPerson(String Person){this.Person.set(Person);}

    public String getTime(){
        return Time.get();
    }

    public SimpleStringProperty TimeProperty(){return Time;}

    public void setTime(String Time){this.Time.set(Time);}

    public boolean isChecked(){
        return checked.get();
    }

    public SimpleBooleanProperty checkProperty(){
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked.set(checked);
    }

    @Override
    public String toString(){
        return "Data{"+
                "Subject="+Subject+
                "Person="+Person+
                "Time="+Time+
                ",checked="+checked+
                "}";
    }
}
