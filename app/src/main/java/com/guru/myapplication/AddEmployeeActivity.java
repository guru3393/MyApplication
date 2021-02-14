package com.guru.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class AddEmployeeActivity extends AppCompatActivity {

    private TextInputEditText edt_name,edt_age,edt_date,edt_manager;
    private Button addEmployee,updateEmployee;
    private EmployeeDataBase employeeDataBase;

    private Employee employee,pre_employee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);



       edt_name = findViewById(R.id.edt_name);
       edt_age = findViewById(R.id.edt_age);
       edt_date = findViewById(R.id.edt_date);
       edt_manager = findViewById(R.id.edt_manager);
       addEmployee = findViewById(R.id.add_employee);
       updateEmployee = findViewById(R.id.update_employee);
       employeeDataBase  = EmployeeDataBase.getInstance(AddEmployeeActivity.this);
       addEmployee.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               employee = new Employee();
               employee.setName(edt_name.getText().toString());
               employee.setAge(Integer.parseInt(edt_age.getText().toString()));
               employee.setDateOfJoining(edt_date.getText().toString());
               employee.setManager(edt_manager.getText().toString());
               new InsertTask(AddEmployeeActivity.this,employee).execute();
           }
       });
        if(getIntent().getStringExtra("EDIT") != null) {
            String edit = getIntent().getStringExtra("EDIT");
            if (edit.equals("true")) {
                pre_employee = getIntent().getParcelableExtra("EMPLOYEE");
                updateEmployee(pre_employee);
            }
        }

    }

    private  static class InsertTask extends AsyncTask<Void,Void,Boolean>{

        private WeakReference<AddEmployeeActivity> activityContext;
        private Employee employee;
        InsertTask(AddEmployeeActivity context, Employee employee){
            this.activityContext = new WeakReference<>(context);
            this.employee = employee;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            activityContext.get().employeeDataBase.getEmployeeDao().insert(employee);
            return true;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                activityContext.get().finish();
            }
        }
    }


    public void updateEmployee(Employee employee){
        addEmployee.setVisibility(View.GONE);
        updateEmployee.setVisibility(View.VISIBLE);
        edt_name.setText(employee.getName());
        edt_age.setText(String.valueOf(employee.getAge()));
        edt_date.setText((employee.getDateOfJoining()));
        edt_manager.setText((employee.getManager()));
        updateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Employee employee = new Employee();
                employee.setEmployee_id(pre_employee.getEmployee_id());
                employee.setName(edt_name.getText().toString());
                employee.setAge(Integer.parseInt(edt_age.getText().toString()));
                employee.setDateOfJoining(edt_date.getText().toString());
                employee.setManager(edt_manager.getText().toString());
                new UpdateTask(AddEmployeeActivity.this,employee).execute();
            }
        });
    }

    private  static class UpdateTask extends AsyncTask<Void,Void,Boolean>{

        private WeakReference<AddEmployeeActivity> activityContext;
        private Employee employee;
        UpdateTask(AddEmployeeActivity context, Employee employee){
            this.activityContext = new WeakReference<>(context);
            this.employee = employee;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            activityContext.get().employeeDataBase.getEmployeeDao().update(employee);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                activityContext.get().finish();
            }
        }
    }

}