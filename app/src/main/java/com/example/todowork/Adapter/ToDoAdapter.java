package com.example.todowork.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todowork.AddNewTask;
import com.example.todowork.MainActivity;
import com.example.todowork.Model.ToDoModel;
import com.example.todowork.R;
import com.example.todowork.Utils.DataBaseHelper;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDoModel> aList;
    private MainActivity activity;
    private DataBaseHelper myDB;

    public ToDoAdapter(DataBaseHelper myDB, MainActivity activity){
        this.activity = activity;
        this.myDB = myDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModel item = aList.get(position);
        holder.checkBox.setText(item.getTask());
        holder.checkBox.setChecked(toBoolean(item.getStatus()));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                if(compoundButton.isChecked()){
                    myDB.updateStatus(item.getId(),1);}
                else{
                    myDB.updateStatus(item.getId(),0);
                }
            }
        });
    }
    public boolean toBoolean(int num) {
        return num != 0;
    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<ToDoModel> aList) {
        this.aList = aList;
        notifyDataSetChanged();
    }

    public void deleteTask(int position) {
        ToDoModel item = aList.get(position);
        myDB.deleteTask(item.getId());

        aList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ToDoModel item = aList.get(position);
         Bundle bundle = new Bundle();
         bundle.putInt("id",item.getId());
         bundle.putString("task",item.getTask());

         AddNewTask task= new AddNewTask();
         task.setArguments(bundle);
         task.show(activity.getSupportFragmentManager(),task.getTag());
    }


    @Override
    public int getItemCount() {
        return aList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }

    }

}
