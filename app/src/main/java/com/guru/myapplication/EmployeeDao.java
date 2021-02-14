package com.guru.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee_table")
    List<Employee> getAllEmployeeList();

    @Insert()
    void  insert (Employee employee);

    @Update()
    void update(Employee employee);


}
