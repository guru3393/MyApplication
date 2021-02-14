package com.guru.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class},version = 1)
public abstract  class EmployeeDataBase extends RoomDatabase {
    public abstract  EmployeeDao getEmployeeDao();

    private static EmployeeDataBase employeeDB;

    public static EmployeeDataBase getInstance(Context context){
        if ( employeeDB == null){
            employeeDB = buildDataBaseInstance(context);
        }
        return employeeDB;
    }

    private static EmployeeDataBase buildDataBaseInstance(Context context){
        return Room.databaseBuilder(context,EmployeeDataBase.class,"EMPLOYEE_DATABASE").allowMainThreadQueries().build();
    }

}
