package com.guru.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_table")
public class Employee implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int employee_id;

    private String name;

    private String dateOfJoining;

    private int age;

    private String manager;

    protected Employee(Parcel in) {

        employee_id = in.readInt();
        name = in.readString();
        age = in.readInt();
        manager = in.readString();
        dateOfJoining = in.readString();
    }

    public Employee() {
    }


    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

//    // Employee Id auto increment
//    public Employee(String name, int age, String dateOfJoining, String manager){
//        this.name = name;
//        this.age = age;
//        this.manager = manager;
//        this.dateOfJoining = dateOfJoining;
//    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(employee_id);
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(manager);
        dest.writeString(dateOfJoining);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}