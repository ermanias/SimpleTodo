package com.example.root.simple_todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    String val;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    EditText etNewItem;
    int selectposition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
              items =new ArrayList<>();

        lvItems = (ListView)findViewById(R.id.lvItems);
        //readItems();
        itemsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        editItems();


        Intent intent =getIntent();
        String val =intent.getStringExtra("lan");
        //items.add(val);
        itemsAdapter.notifyDataSetChanged();


    }


    public void editItems(){
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View itemValue, int position, long id){

                selectposition=position;
                Intent intent = new Intent(MainActivity.this, Edit.class);


                intent.putExtra("value" ,items.get(position));
                startActivityForResult(intent,0);
                itemsAdapter.notifyDataSetChanged();

            }
        });
    }
    public void onAddItem(View v){
        etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        //writeItems();
    }

    public void modifier(){

        String name= etNewItem.getText().toString();
        int pos= lvItems.getCheckedItemPosition();
        if (!name.isEmpty() && name.length()>0);
        itemsAdapter.insert(name,pos);
        itemsAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"update" + name,Toast.LENGTH_SHORT).show();


    }

    public void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id){
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                String Nname =data.getStringExtra("New");
                items.set(selectposition,Nname);
                itemsAdapter.notifyDataSetChanged();
                break;


        }

    }


}
