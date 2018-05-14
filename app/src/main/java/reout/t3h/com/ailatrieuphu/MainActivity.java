package reout.t3h.com.ailatrieuphu;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import reout.t3h.com.ailatrieuphu.dialog.ShowHightscore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer musicBg = null;
    private MediaPlayer musicPress = null;
    public static boolean IS_CALLED = false;
    public static boolean IS_5050 = false;
    public static boolean IS_RESET = false;
    public static boolean isPlaying = true;
    public static int id;
    public static int money;
    public static int questionPass = 0;

    private TextView tvName;
    private TextView tvQuestionPass;
    private TextView tvMoney;
    // private Button btnHightscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_layout);
        initview();
    }

    private void initview() {
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_hight_score).setOnClickListener(this);
        musicBg = MediaPlayer.create(this, R.raw.bgmusic);
        //  musicBg.setVolume(3.0f,3.0f);
        musicBg.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                turnOffMusicBg();
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), GuideActivity.class);
                startActivity(intent);
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

    private void turnOffMusicBg() {
        if (this.musicBg != null) {
            if (this.musicBg.isPlaying()) {
                this.musicBg.stop();
            }
            this.musicBg.release();
            this.musicBg = null;
        }
    }


}
