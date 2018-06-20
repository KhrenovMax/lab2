package com.example.polina.labs1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText enterPass;
    private Button sendForm;
    private final String password = "admin";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterPass = (EditText) findViewById(R.id.enterPass);
        sendForm = (Button) findViewById(R.id.sendForm);

        sendForm.setOnClickListener(this);
        preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);

        int auth = preferences.getInt("auth",0);

        if(auth == 1){
            Intent intent = new Intent(this,TestActivity.class);
            startActivity(intent);
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {

        String editVarPass = enterPass.getText().toString().trim();

        if(editVarPass.equalsIgnoreCase(password)){

            SharedPreferences.Editor memory = preferences.edit();

            memory.putInt("auth", 1);
            memory.apply();

            // open new page
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Неправильный пароль", Toast.LENGTH_LONG).show();
        }

    }
}
