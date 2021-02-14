package com.guru.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {


    private List<Employee> mEmployee;

    // Pass in the contact array into the constructor
    public EmployeeListAdapter(List<Employee> contacts) {
        mEmployee = contacts;
    }


    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.employee_item, parent, false);

        // Return a new holder instance
        EmployeeViewHolder viewHolder = new EmployeeViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bind(mEmployee.get(position),holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mEmployee.size();
    }



    static class EmployeeViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView age;
        private TextView date;
        private TextView manager;
        public EmployeeViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employee_name);
            age = itemView.findViewById(R.id.employee_age);
            date = itemView.findViewById(R.id.date);
            manager = itemView.findViewById(R.id.manager);

        }

        public void bind(final Employee employee, View holder){
            name.setText(employee.getName());
            age.setText(String.valueOf(employee.getAge()));
            date.setText(employee.getDateOfJoining());
            manager.setText(employee.getManager());
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(itemView.getContext(),AddEmployeeActivity.class);
                    intent.putExtra("EDIT","true");
                    intent.putExtra("EMPLOYEE",employee);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        static EmployeeViewHolder create(ViewGroup parent){
            View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.employee_item,parent,false);
            return new EmployeeViewHolder(view);
        }
    }
}
