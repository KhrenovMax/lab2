package com.example.polina.labs1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text_result;
    private TextView rightAnswerMessage;
    private TextView wrongAnswerMessage;


    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        text_result = (TextView) findViewById(R.id.text_result);
        rightAnswerMessage = (TextView) findViewById(R.id.rightAnswerMessage);
        wrongAnswerMessage = (TextView) findViewById(R.id.wrongAnswerMessage);

        Intent intent = getIntent();
        int wrong_answers = Integer.parseInt(intent.getExtras().get("wrong_answers").toString());
        int right_answers = 5 - wrong_answers;

        if (right_answers * 100 / 5 >= 71){
            text_result.setText("Тест успешно пройден");
        }
        else {
            text_result.setText("Тест не пройден");
        }
        rightAnswerMessage.setText("Правильных ответов: " + right_answers);
        wrongAnswerMessage.setText("Неправильных ответов: " + wrong_answers);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        Intent intent = null;

        switch(id){
            case R.id.menu_add:
                // open page About App
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_edit:
                // exit App and clear cache
                preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("is_auth", 0);
                editor.apply();

                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
