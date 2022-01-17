package lv.example.repository_g_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private Button read_preferences;
    private Button back;
    private SharedPreferences DB;
    private String user_input_str;
    private EditText user_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        read_preferences = (Button) findViewById(R.id.read_preferences);
        back = (Button) findViewById(R.id.back);
        DB = (SharedPreferences) getApplicationContext().getSharedPreferences("DataBase", Context.MODE_PRIVATE);
        user_input_str = (String) DB.getString("user_input","");
        user_input = (EditText) findViewById(R.id.readInput);
        if(user_input_str.equals("")) {
            String output = "Nothing found";
            user_input.setText(output);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();

            }
        });

        read_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPreferences();

            }
        });
    }

    public void goBack() {
        Intent intent  = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void readPreferences() {
        user_input.setText(user_input_str);
    }
}