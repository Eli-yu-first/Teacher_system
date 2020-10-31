package com.hnust.domain;

import javafx.beans.property.SimpleStringProperty;

public class Data {
    private SimpleStringProperty Subject=new SimpleStringProperty();
    private SimpleStringProperty Person=new SimpleStringProperty();
    private SimpleStringProperty Time=new SimpleStringProperty();
    private SimpleStringProperty Operate=new SimpleStringProperty();
    public Data(String Subject,String Person,String Time,String Operate){
        this.Subject.set(Subject);
        this.Person.set(Person);
        this.Time.set(Time);
        this.Operate.set(Operate);
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

    public String getOperate(){
        return Operate.get();
    }

    public SimpleStringProperty OperateProperty(){return Operate;}

    public void setOperate(String Operate){this.Operate.set(Operate);}

    @Override
    public String toString(){
        return "Data{"+
                "Subject="+Subject+
                "Person="+Person+
                "Time="+Time+
                "Operate="+Operate+
                "}";
    }
}
