package com.example.polina.labs1;
import java.io.Serializable;

class Questions implements Serializable {
    private String quest_text;
    private String var1;
    private String var2;
    private String var3;
    private String right_answer;

    public Questions(String quest_text, String var1, String var2, String var3, String right_answer) {
        this.quest_text = quest_text;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.right_answer = right_answer;
    }

    public String quest_text(){

        return quest_text;
    }

    public String var1_quest(){

        return var1;
    }
    public String var2_quest(){

        return var2;
    }
    public String var3_quest(){

        return var3;
    }
    public String right_answer_quest(){

        return right_answer;
    }

    public void set_quest_text(String quest_text) {
        this.quest_text = quest_text;
    }

    public void set_var1(String var1) {

        this.var1 = var1;
    }
    public void set_var2(String var2) {

        this.var2 = var2;
    }
    public void set_var3(String var3) {
        this.var1 = var3;
    }
    public void set_right_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public String toString() {
        return "Questions{" +
                "text='" + quest_text + '\'' +
                ", variant_1='" + var1 + '\'' +
                ", variant_2='" + var2 + '\'' +
                ", variant_3='" + var3 + '\'' +
                ", right_answer=" + right_answer +
                '}';
    }



}