package com.hnust.mock;

import com.hnust.domain.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataList {
    public static ObservableList<Data> initData(){
        ObservableList<Data> list=FXCollections.observableArrayList();
        Data d1=new Data("数据结构","张三","20201030");
        Data d2=new Data("数据结构","张三","20201030");
        Data d3=new Data("数据结构","张三","20201030");
        Data d4=new Data("数据结构","张三","20201030");
        Data d5=new Data("数据结构","张三","20201030");
        Data d6=new Data("数据结构","张三","20201030");
        Data d7=new Data("数据结构","张三","20201030");
        Data d8=new Data("数据结构","张三","20201030");
        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);
        list.add(d5);
        list.add(d6);
        list.add(d7);
        list.add(d8);
        return list;
    }
}
