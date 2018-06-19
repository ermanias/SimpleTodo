package com.example.root.simple_todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {


    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    String value;
    EditText tv;
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        savebtn = (Button) findViewById(R.id.button);

        tv = (EditText) findViewById(R.id.editText2);
        Intent intent = getIntent();
        value = intent.getStringExtra("value");

        tv.setText(value);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Nname = tv.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("New", Nname);
                setResult(RESULT_OK, intent);
                finish();


                Toast.makeText(Edit.this, "modification has been successfully", Toast.LENGTH_LONG).show();

            }
        });

    }
}