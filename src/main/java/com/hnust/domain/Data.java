package com.hnust.domain;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Data {
    private SimpleIntegerProperty Id=new SimpleIntegerProperty();
    private SimpleStringProperty Content = new SimpleStringProperty();
    private SimpleStringProperty Course = new SimpleStringProperty();
    private SimpleStringProperty Chapter = new SimpleStringProperty();
    private SimpleStringProperty Type = new SimpleStringProperty();
    private SimpleStringProperty Difficulty = new SimpleStringProperty();
    private SimpleStringProperty Parsing = new SimpleStringProperty();
    private SimpleStringProperty Knowledge = new SimpleStringProperty();
    private SimpleStringProperty Answer = new SimpleStringProperty();
    private SimpleStringProperty Objectives = new SimpleStringProperty();
    private SimpleStringProperty Permissions = new SimpleStringProperty();
    private SimpleStringProperty Operation = new SimpleStringProperty();
    private SimpleBooleanProperty checked=new SimpleBooleanProperty();

    public Data(Integer Id, String Content,String Course, String Chapter, String Type,
                String Difficulty, String Parsing, String Knowledge, String Answer,
                String Objectives, String Permissions, String Operation)
    {
        this.Id.set(Id);
        this.Content.set(Content);
        this.Course.set(Course);
        this.Chapter.set(Chapter);
        this.Type.set(Type);
        this.Difficulty.set(Difficulty);
        this.Parsing.set(Parsing);
        this.Knowledge.set(Knowledge);
        this.Answer.set(Answer);
        this.Objectives.set(Objectives);
        this.Permissions.set(Permissions);
        this.Operation.set(Operation);
    }

    public int getId() {
        return Id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public String getContent() {
        return Content.get();
    }

    public SimpleStringProperty contentProperty() {
        return Content;
    }

    public void setContent(String content) {
        this.Content.set(content);
    }

    public String getCourse() {
        return Course.get();
    }

    public SimpleStringProperty courseProperty() {
        return Course;
    }

    public void setCourse(String course) {
        this.Course.set(course);
    }

    public String getChapter() {
        return Chapter.get();
    }

    public SimpleStringProperty chapterProperty() {
        return Chapter;
    }

    public void setChapter(String chapter) {
        this.Chapter.set(chapter);
    }

    public String getType() {
        return Type.get();
    }

    public SimpleStringProperty typeProperty() {
        return Type;
    }

    public void setType(String type) {
        this.Type.set(type);
    }

    public String getDifficulty() {
        return Difficulty.get();
    }

    public SimpleStringProperty difficultyProperty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.Difficulty.set(difficulty);
    }

    public String getParsing() {
        return Parsing.get();
    }

    public SimpleStringProperty parsingProperty() {
        return Parsing;
    }

    public void setParsing(String parsing) {
        this.Parsing.set(parsing);
    }

    public String getKnowledge() {
        return Knowledge.get();
    }

    public SimpleStringProperty knowledgeProperty() {
        return Knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.Knowledge.set(knowledge);
    }

    public String getAnswer() {
        return Answer.get();
    }

    public SimpleStringProperty answerProperty() {
        return Answer;
    }

    public void setAnswer(String answer) {
        this.Answer.set(answer);
    }

    public String getObjectives() {
        return Objectives.get();
    }

    public SimpleStringProperty objectivesProperty() {
        return Objectives;
    }

    public void setObjectives(String objectives) {
        this.Objectives.set(objectives);
    }

    public String getPermissions() {
        return Permissions.get();
    }

    public SimpleStringProperty permissionsProperty() {
        return Permissions;
    }

    public void setPermissions(String permissions) {
        this.Permissions.set(permissions);
    }

    public String getOperation() {
        return Operation.get();
    }

    public SimpleStringProperty operationProperty() {
        return Operation;
    }

    public void setOperation(String operation) {
        this.Operation.set(operation);
    }
    public boolean isChecked() {
        return checked.get();
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    @Override
    public String toString() {
        return "Data{" +
                "Id=" + Id +
                ", Content=" + Content +
                ", Course=" + Course +
                ", Chapter=" + Chapter +
                ", Type=" + Type +
                ", Difficulty=" + Difficulty +
                ", Parsing=" + Parsing +
                ", Knowledge=" + Knowledge +
                ", Answer=" + Answer +
                ", Objectives=" + Objectives +
                ", Permissions=" + Permissions +
                ", Operation=" + Operation +
                '}';
    }
}
