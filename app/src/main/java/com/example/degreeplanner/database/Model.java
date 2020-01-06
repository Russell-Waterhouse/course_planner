package com.example.degreeplanner.database;

import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class Model extends Repository{

    private boolean editing;
    private List<Observer> observers;

    public Model(Application app){
        super(app);
        observers = new LinkedList<>();
        editing = false;
    }

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    public void modelUpdates(){
        for(Observer observer : observers){
            observer.onModelUpdate();
        }
    }

    public boolean isEditing(){
        return editing;
    }
}
