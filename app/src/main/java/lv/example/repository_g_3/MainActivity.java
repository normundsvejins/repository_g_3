package lv.example.repository_g_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button save;
    private Button go_to_second;
    private EditText user_input;
    private String user_input_str;
    private SharedPreferences DB;
    private String DB_data;
    private Spinner drop_down;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.save);
        go_to_second = (Button) findViewById(R.id.go_to_second);
        user_input = (EditText) findViewById(R.id.textInput);
        DB = (SharedPreferences) getSharedPreferences("DataBase", Context.MODE_PRIVATE);
        DB_data = DB.getString("user_input","");
        drop_down = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.themes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_down.setAdapter(adapter);
        drop_down.setOnItemSelectedListener(this);

        user_input.setText(DB_data);
        go_to_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecond();

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInput();
            }
        });
    }
    public void goToSecond() {
        Intent intent  = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void saveInput() {
        user_input_str = user_input.getText().toString();
        SharedPreferences.Editor editor = DB.edit();
        editor.putString("user_input",user_input_str);
        editor.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String theme = parent.getItemAtPosition(position).toString();
        if(theme.equals("Dark theme")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        if(theme.equals("Light theme")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if(theme.equals("Default")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}