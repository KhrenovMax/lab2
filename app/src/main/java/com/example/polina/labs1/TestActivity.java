package com.example.polina.labs1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
public class TestActivity extends AppCompatActivity implements View.OnClickListener{


    private Button getbutton;
    private RadioGroup radioGroup;
    private TextView text_question;
    private RadioButton radio_group1;
    private RadioButton radio_group2;
    private RadioButton radio_group3;

    private SharedPreferences preferences;

    private int index = 0;
    private int wrong_answers = 0;


    public Questions question_1 = new Questions("Сколько будет 2+2*2?","4","6","8","6");
    public Questions question_2 = new Questions("Сколько раз Путин президент?","5","3","4","4");
    public Questions question_3 = new Questions("Чем слышит кузнечик?","головой","ногами","нет слуха","ногами");
    public Questions question_4 = new Questions("Сколько сердец у осьминога?","1","2","3","3");
    public Questions question_5 = new Questions("Самый большой человеческий орган","язык","мозг","кожа","кожа");


    public Questions[] questions_arr = {
            question_1,
            question_2,
            question_3,
            question_4,
            question_5
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        text_question = (TextView) findViewById(R.id.text_question);
        radio_group1 = (RadioButton) findViewById(R.id.radio_group1);
        radio_group2 = (RadioButton) findViewById(R.id.radio_group2);
        radio_group3 = (RadioButton) findViewById(R.id.radio_group3);

        getbutton = (Button) findViewById(R.id.getbutton);
        getbutton.setOnClickListener(this);

        ChangeQuestion();
        preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void ChangeQuestion(){
        text_question.setText(questions_arr[index].quest_text());
        radio_group1.setText(questions_arr[index].var1_quest());
        radio_group2.setText(questions_arr[index].var2_quest());
        radio_group3.setText(questions_arr[index].var3_quest());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        Intent intent = null;

        switch(id){
            case R.id.menu_add:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_edit:
                preferences = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("auth", 0);
                editor.apply();

                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        RadioButton getbutton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        String answer = getbutton.getText().toString().trim();

        if (questions_arr[index].right_answer_quest().equalsIgnoreCase(answer)) Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "Неверный ответ", Toast.LENGTH_SHORT).show();
            wrong_answers++;
        }

        if (index < 4){
            index++;
            ChangeQuestion();
        }
        else{
            Intent intent = new Intent(TestActivity.this, ResultActivity.class);
            intent.putExtra("wrong_answers", wrong_answers);
            startActivity(intent);
        }
    }
}

