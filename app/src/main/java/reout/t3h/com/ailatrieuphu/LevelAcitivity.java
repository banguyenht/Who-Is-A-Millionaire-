package reout.t3h.com.ailatrieuphu;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nguye on 3/8/2018.
 */

public class LevelAcitivity extends AppCompatActivity {
    private TextView question1;
    private TextView question2;
    private TextView question3;
    private TextView question4;
    private TextView question5;
    private TextView question6;
    private TextView question7;
    private TextView question8;
    private TextView question9;
    private TextView question10;
    private TextView question11;
    private TextView question12;
    private TextView question13;
    private TextView question14;
    private TextView question15;
    private int currentLevel;
    private MediaPlayer musicQuestion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_layout);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        init();
        setBackground();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startMusic();
                Intent intent=new Intent();
              //  overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                intent.setClass(LevelAcitivity.this,PlayActivity.class);

                startActivity(intent);
//
                finish();
            }
        },3000);
    }
    private void setBackground(){
        switch (this.currentLevel){
            case 2:
                question2.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 3:
                question3.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 4:
                question4.setBackgroundResource(R.drawable.bg_choose2);
                break;
                case 1:
                question1.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 5:
                question5.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 6:
                question6.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 7:
                question7.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 8:
                question8.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 9:
                question9.setBackgroundResource(R.drawable.bg_choose2);
                break;
            case 10:
                question10.setBackgroundResource(R.drawable.bg_choose2);
                break;

        }
    }
    private void startMusic(){
        switch (this.currentLevel) {
            case 1:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques1);
                break;
            case 2:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques2);
                break;
            case 3:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques3_b);
                break;
            case 4:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques4);
                break;
            case 5:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques5);
                break;
            case 6:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques6);
                break;
            case 7:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques7_b);
                break;
            case 8:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques8);
                break;
            case 9:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques9_b);
                break;
            case 10:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques10);
                break;
            case 11:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques11);
                break;
            case 12:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques12);
                break;
            case 13:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques13);
                break;
            case 14:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques14
                );
                break;
            case 15:
                this.musicQuestion = MediaPlayer.create(this, R.raw.ques15
                );
                break;
            default:
                break;
        }
        this.musicQuestion.start();
    }
    private void init(){
        Intent intent=getIntent();
       // overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        this.currentLevel=intent.getIntExtra("currentquestion",1);
        startMusic();
     //   Toast.makeText(this,"level: "+String.valueOf(this.currentLevel),Toast.LENGTH_SHORT).show();
        question1=(TextView)findViewById(R.id.question1);
        question2=(TextView)findViewById(R.id.question2);
        question3=(TextView)findViewById(R.id.question3);
        question4=(TextView)findViewById(R.id.question4);
        question5=(TextView)findViewById(R.id.question5);
        question6=(TextView)findViewById(R.id.question6);
        question7=(TextView)findViewById(R.id.question7);
        question8=(TextView)findViewById(R.id.question8);
        question9=(TextView)findViewById(R.id.question9);
        question10=(TextView)findViewById(R.id.question10);
        question11=(TextView)findViewById(R.id.question11);
        question12=(TextView)findViewById(R.id.question12);
        question13=(TextView)findViewById(R.id.question13);
        question14=(TextView)findViewById(R.id.question14);
        question15=(TextView)findViewById(R.id.question15);


    }
}
