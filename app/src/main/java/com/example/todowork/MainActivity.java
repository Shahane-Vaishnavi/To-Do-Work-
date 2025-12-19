package com.example.todowork;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ListView list;
    private Button button;

    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        button= findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem(view);
            }
        });
        items=new ArrayList<>();
        itemsAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);
        /*
        list.setOnClickListener(new AdapterView.OnItemLongClickListener()
            @Override
                boolean onItemLongClick(AdapterView<?> parent, View view, ){
                    return remove(position);

                 }
            });*/

    }
/*
    private boolean remove(int position) {
        Context context = getApplicationContext();
        Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();
        items.remove(position);
        itemsAdapter.notifDataSetChanged();
        return true;
    }*/


    private void additem(View view){
        EditText input= findViewById(R.id.edit_text);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Enter Text...!", Toast.LENGTH_LONG).show();
        }

    }
}