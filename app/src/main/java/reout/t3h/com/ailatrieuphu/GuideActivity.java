package reout.t3h.com.ailatrieuphu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer musicLuatChoi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_layout);
//        initview();
        findViewById(R.id.btnctn).setOnClickListener(this);
        musicLuatChoi = MediaPlayer.create(this, R.raw.luatchoi_b);
        musicLuatChoi.start();
    }

    private void initview() {
        musicLuatChoi = MediaPlayer.create(this, R.raw.luatchoi_b);
//        if (MainActivity.isPlaying) {
//           musicLuatChoi.start();
//            findViewById(R.id.btnctn).setOnClickListener(this);
//            MainActivity.isPlaying = false;
//        }
            Intent intent = new Intent();
            intent.setClass(GuideActivity.this, PlayActivity.class);
            startActivity(intent);
            finish();
            stopMusic();
    }

    @Override
    public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(this.getBaseContext(), PlayActivity.class);
        intent.setClass(this.getBaseContext(), LevelAcitivity.class);
            startActivity(intent);
            finish();
            stopMusic();
    }

    private void stopMusic() {
        if (this.musicLuatChoi != null) {
            if (musicLuatChoi.isPlaying()) {
                musicLuatChoi.stop();
            }
            this.musicLuatChoi.release();
            musicLuatChoi = null;
        }
    }

    @Override
    protected void onDestroy() {
        this.musicLuatChoi = null;
        super.onDestroy();
    }
}
