package com.example.degreeplanner.database;

import android.app.Application;

public class Model extends Repository{

    private boolean editing;

    public Model(Application app){
        super(app);
        editing = false;
    }
}
