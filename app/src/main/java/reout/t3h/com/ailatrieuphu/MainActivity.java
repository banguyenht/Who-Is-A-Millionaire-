package reout.t3h.com.ailatrieuphu;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import reout.t3h.com.ailatrieuphu.dialog.SaveScore;
import reout.t3h.com.ailatrieuphu.dialog.ShowHightscore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer musicBg = null;
    private MediaPlayer musicPress = null;
    public static boolean isPlaying = true;
    public static int id;
//    public static boolean IS_CALLED = false;
//    public static boolean IS_5050 = false;
//    public static boolean IS_RESET = false;
//    public static boolean IS_AUDIENCE = false;
public static boolean IS_CALLED;
    public static boolean IS_5050;
    public static boolean IS_RESET;
    public static boolean IS_AUDIENCE;
    public static int questionPass = 0;
//    public static boolean isHightscore;

    //  public static boolean isPlaying = true;
    public static int money;
//    public static int money;

    @Override
    protected void onStart() {
        super.onStart();
        IS_CALLED = false;
        IS_5050 = false;
        IS_RESET = false;
        IS_AUDIENCE = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.begin_layout);
            initview();

        if(PlayActivity.isHightscore){
            Log.d("ishight","update higtscore");
            SaveScore saveScore=new SaveScore(this);
            saveScore.show();
        }
        else { Log.d("ishight","not update higtscore");

        }
    }

    private void initview() {
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_hight_score).setOnClickListener(this);
        musicBg = MediaPlayer.create(this, R.raw.bgmusic);
        musicBg.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), GuideActivity.class);
                startActivity(intent);

        musicBg.stop();
                finish();
                return;
            case R.id.btn_hight_score:
                ShowHightscore showHightscore = new ShowHightscore(this);
//                    DataBasemanager dataBasemanager=new DataBasemanager(getApplicationContext());
//                List<HightScore>hightScores=dataBasemanager.getHightscore();
//                tvName.setText(hightScores.get(0).getName());
//                DataBasemanager dataBasemanager=new DataBasemanager(this);
//                dataBasemanager.insertHightscore(new HightScore());
                showHightscore.show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("stop","stop activity");
        musicBg.release();
    }

}
