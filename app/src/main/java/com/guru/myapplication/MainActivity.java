package com.guru.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button_add;
    private RecyclerView listView;
    private EmployeeDataBase employeeDataBase;
    private EmployeeListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeeDataBase  = EmployeeDataBase.getInstance(MainActivity.this);
        button_add =  findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainActivity.this,AddEmployeeActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        listView = findViewById(R.id.recyclerView);

        listView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        new  RetriveTask(this).execute();
    }

    private  static class RetriveTask extends AsyncTask<Void,Void, List<Employee>> {

        private WeakReference<MainActivity> activityContext;


        public RetriveTask(MainActivity mainActivity) {
            activityContext = new WeakReference<>(mainActivity);
        }

        @Override
        protected List<Employee> doInBackground(Void... voids) {

           return  activityContext.get().employeeDataBase.getEmployeeDao().getAllEmployeeList();
        }

        @Override
        protected void onPostExecute(List<Employee> employees) {
            super.onPostExecute(employees);

            if(employees!= null && employees.size() > 0 ){
                activityContext.get().adapter = new EmployeeListAdapter(employees);
                activityContext.get().listView.setAdapter(new EmployeeListAdapter(employees));
            }
        }
    }
}