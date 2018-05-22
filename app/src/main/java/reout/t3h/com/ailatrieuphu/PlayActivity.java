package reout.t3h.com.ailatrieuphu;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import reout.t3h.com.ailatrieuphu.dialog.CallDialog;
import reout.t3h.com.ailatrieuphu.dialog.SaveScore;
import reout.t3h.com.ailatrieuphu.dialog.SupportAudition;


public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private int currentMoney;
    private List<HightScore> hightScoreList;
    private int time = 30;
    private Handler mHandler;
    private static final int HANDLERTIME = 100;
    private DataBasemanager dataBasemanager;
    private Button ra;
    private Button rb;
    private Button rc;
    private Button rd;
    private ImageButton reset;
    private int player_answer;
    public static boolean isHightscore;
    public static final int ANSWER_A = 1;
    public static final int ANSWER_B = 2;
    public static final int ANSWER_C = 3;
    public static final int ANSWER_D = 4;


    private TextView ask;
    private MediaPlayer musicSelect;
    private MediaPlayer musicIsTrue;
    private MediaPlayer music5050;
    private ImageButton btnpercen50;
    private ImageView btncall;
    private ImageView btnStop;
    private ImageView btnAudience;

    private TextView tvTime;
    private TextView tvMoney;
    private Question curentQuestion;
    private int currentLevel = 1;
    private MediaPlayer musicQuestion;
    private Thread mThread;
    private boolean runningThread = true;
    private boolean isClickAns = false;
    private Button butnChoose;
    private boolean checkClick = true;
    ImageView btnx;
    ImageView btnx5050;
    ImageView btnxReset;
    ImageView btnxAudience;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);
        dataBasemanager = new DataBasemanager(this);
        hightScoreList = dataBasemanager.getHightscore();
        init();
        setContent();
        initHandler();
        initThread();
        setMoney();
        if (MainActivity.IS_CALLED == false) {
            btnx.setVisibility(View.INVISIBLE);
        }
        if (MainActivity.IS_CALLED == true) {
            btnx.setVisibility(View.VISIBLE);
        }
        if (MainActivity.IS_5050 == false) {
            btnx5050.setVisibility(View.INVISIBLE);
        }
        if (MainActivity.IS_5050 == true) {
            btnx5050.setVisibility(View.VISIBLE);
        }
        /////
        if (MainActivity.IS_RESET == true) {
            btnxReset.setVisibility(View.VISIBLE);
        }
        if (MainActivity.IS_RESET == false) {
            btnxReset.setVisibility(View.INVISIBLE);
        }
        if (MainActivity.IS_AUDIENCE == true) {
            btnxAudience.setVisibility(View.VISIBLE);
        }
        if (MainActivity.IS_AUDIENCE == false) {
            btnxAudience.setVisibility(View.INVISIBLE);
        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        DataBasemanager.level=1;
//
//    }

    private void initHandler() {
        this.mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == HANDLERTIME) {
                    tvTime.setText(String.valueOf(msg.arg1));
                }
            }
        };
    }

    private void initThread() {
        this.mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                runThread();
            }
        });
        this.mThread
                .start();
    }

    private void runThread() {

        while (this.runningThread) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg;
            if (!isClickAns && this.time >= 0) {

                msg = new Message();
                msg.what = HANDLERTIME;
                msg.arg1 = this.time;
                mHandler.sendMessage(msg);
                this.time--;
            }
            if (time < 0)
                runningThread = false;

        }
        if (!runningThread && !isClickAns) {

            Intent intent = new Intent();
            intent.setClassName(PlayActivity.this, MainActivity.class.getName());
            startActivity(intent);
            finish();
        }

    }

    private void setMoney() {
        switch (DataBasemanager.level - 1) {
            case 1:
                MainActivity.money = 200000;
                MainActivity.questionPass++;
                tvMoney.setText("200.000");
                break;
            case 2:
                MainActivity.money = 400000;
                MainActivity.questionPass++;
                tvMoney.setText("400.000");
                break;
            case 3:
                MainActivity.money = 600000;
                MainActivity.questionPass++;
                tvMoney.setText("600.000");
                break;
            case 4:
                MainActivity.money = 1000000;
                MainActivity.questionPass++;
                tvMoney.setText("1.000.000");
                break;
            case 5:
                MainActivity.money = 2000000;
                MainActivity.questionPass++;

                tvMoney.setText("2.000.000");
                break;
            case 6:
                MainActivity.money = 3000000;
                MainActivity.questionPass++;
                tvMoney.setText("3.000.000");
                break;
            case 7:
                MainActivity.money = 6000000;
                MainActivity.questionPass++;
                tvMoney.setText("6.000.000");
                break;
            case 8:
                MainActivity.money = 10000000;
                MainActivity.questionPass++;
                tvMoney.setText("10.000.000");
                break;
            case 9:
                tvMoney.setText("14.000.000");
                break;
            case 10:
                tvMoney.setText("22.000.000");
                break;
            case 11:
                tvMoney.setText("30.000.000");
                break;
            case 12:
                tvMoney.setText("40.000.000");
                break;
            case 13:
                tvMoney.setText("60.000.000");
                break;
            case 14:
                tvMoney.setText("85.000.000");
                break;
            case 15:
                tvMoney.setText("150.000.000");
                break;
        }
    }


    private void init() {
        dataBasemanager = new DataBasemanager(this);
//

        tvTime = (TextView) findViewById(R.id.tvtime);
        // tvTime.setText(String.valueOf(time));
        ask = findViewById(R.id.ask);
        ra = (Button) findViewById(R.id.ra);
        ra.setOnClickListener(this);
        rb = (Button) findViewById(R.id.rb);
        rb.setOnClickListener(this);
        rc = (Button) findViewById(R.id.rc);
        rc.setOnClickListener(this);
        rd = (Button) findViewById(R.id.rd);
        rd.setOnClickListener(this);
        btnpercen50 = (ImageButton) findViewById(R.id.btnpercen50);
        btnpercen50.setOnClickListener(this);
        findViewById(R.id.btnpercen50).setOnClickListener(this);
        if (!MainActivity.IS_CALLED) {
            btncall = (ImageView) findViewById(R.id.btncall);
            btncall.setOnClickListener(this);
        }

        btnStop = (ImageView) findViewById(R.id.btnstop);
        btnStop.setOnClickListener(this);
        btnAudience = (ImageView) findViewById(R.id.btnAudience);
        btnAudience.setOnClickListener(this);
        reset = (ImageButton) findViewById(R.id.btnrestart);
        reset.setOnClickListener(this);
        tvTime = (TextView) findViewById(R.id.tvtime);
        tvMoney = (TextView) findViewById(R.id.tvmoney);
        btnx = findViewById(R.id.btncallx);
        btnx5050 = findViewById(R.id.btncallx50);
        btnx5050.setVisibility(View.INVISIBLE);
        btnxReset = findViewById(R.id.btnxReset);
        btnxReset.setVisibility(View.INVISIBLE);
        btnxAudience = findViewById(R.id.btnAudiencex);
        btnxAudience.setVisibility(View.INVISIBLE);
    }

    private void setContent() {
        this.curentQuestion = dataBasemanager.getQuestion();
        String ask = this.curentQuestion.getAsk();
        String ra = curentQuestion.getRa();
        String rb = curentQuestion.getRb();
        String rc = curentQuestion.getRc();
        String rd = curentQuestion.getRd();
        this.ask.setText(ask);
        this.ra.setText(ra);
        this.rb.setText(rb);
        this.rc.setText(rc);
        this.rd.setText(rd);
    }


    private boolean isAnsCorect() {
        if (this.player_answer == this.curentQuestion.getAn()) {
            return true;
        }
        return false;
    }

    private int randomNumber(int limit) {
        return new Random().nextInt(limit) + 1;
    }

    @Override
    public void onClick(View view) {
        Handler handlerClick = new Handler();
        switch (view.getId()) {
            case R.id.ra:
                this.runningThread = false;
                this.isClickAns = true;
                switch (randomNumber(2)) {
                    case 1:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_a);
                        break;
                    case 2:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_a2);
                    default:
                }
                this.player_answer = ANSWER_A;
                this.butnChoose = this.ra;
                rb.setEnabled(false);
                rc.setEnabled(false);
                rd.setEnabled(false);
                ra.setBackgroundResource(R.drawable.chose_bg);
                AnimationDrawable frameAnimationa = (AnimationDrawable) ra.getBackground();
                frameAnimationa.start();
                musicSelect.start();
                isClickAns = true;
                if (isAnsCorect()) {
                    // Log.d("Level", String.valueOf(DataBasemanager.level));
                    DataBasemanager.level++;
                    // Handler handler = new Handler();
                    SetAnimationCorect setAnimation = new SetAnimationCorect(ra);
                    handlerClick.postDelayed(setAnimation, 2000);
                    //dùng handler để delay màn hình . arg1: 1 runable, agr2 thời gian dừng
                    // dừng 2s mới nháy câu đúng
                    switch (randomNumber(3)) {
                        case 1:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_a);
                            break;
                        case 2:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_a2);
                            break;
                        case 3:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_a3);
                            break;
                        default:
                            break;
                    }
                    delay();
                } else {

                    setFalse();//nháy đáp án đúng nếu người chơi chọn sai
                }
                return;
            case R.id.rb:
                this.runningThread = false;
                this.isClickAns = true;
                ra.setEnabled(false);
                rc.setEnabled(false);
                rd.setEnabled(false);
                rb.setBackgroundResource(R.drawable.chose_bg);
                ((AnimationDrawable) this.rb.getBackground()).start();
                switch (randomNumber(2)) {
                    case 1:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_b);
                        break;
                    case 2:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_b2);
                    default:
                        break;

                }

                this.player_answer = ANSWER_B;
                this.butnChoose = this.rb;
                musicSelect.start();

                if (isAnsCorect()) {
                    switch (randomNumber(3)) {
                        case 1:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_b);
                            break;
                        case 2:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_b2);
                            break;
                        case 3:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_b3);
                            break;
                        default:
                            break;
                    }
                    DataBasemanager.level++;
                    SetAnimationCorect setAnimation = new SetAnimationCorect(rb);
                    handlerClick.postDelayed(setAnimation, 2000);
                    delay();
                } else {
                    setFalse();

                }

                return;
            case R.id.rc:
                this.isClickAns = true;
                this.runningThread = false;
                rb.setEnabled(false);
                rd.setEnabled(false);
                ra.setEnabled(false);
                rc.setBackgroundResource(R.drawable.chose_bg);
                ((AnimationDrawable) this.rc.getBackground()).start();
                switch (randomNumber(2)) {
                    case 1:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_c);
                        break;
                    case 2:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_c2);
                    default:
                        break;
                }
                this.player_answer = ANSWER_C;
                this.butnChoose = this.rc;
                musicSelect.start();

                if (isAnsCorect()) {
                    switch (randomNumber(3)) {
                        case 1:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_c);
                            break;
                        case 2:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_c2);
                        case 3:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_c3);
                        default:
                            break;

                    }
                    DataBasemanager.level++;
                    // Handler handler = new Handler();
                    SetAnimationCorect setAnimation = new SetAnimationCorect(rc);
                    handlerClick.postDelayed(setAnimation, 2000);

                    delay();
                } else {

                    setFalse();

                }
                return;
            case R.id.rd:
                this.isClickAns = true;
                this.runningThread = false;
                rd.setBackgroundResource(R.drawable.chose_bg);
                ((AnimationDrawable) this.rd.getBackground()).start();
                switch (randomNumber(2)) {
                    case 1:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_d);
                        break;
                    case 2:
                        this.musicSelect = MediaPlayer.create(this, R.raw.ans_d2);
                    default:
                        break;

                }
                rb.setEnabled(false);
                rc.setEnabled(false);
                ra.setEnabled(false);
                this.player_answer = ANSWER_D;
                this.butnChoose = this.rd;
                musicSelect.start();

                if (isAnsCorect()) {
                    switch (randomNumber(2)) {
                        case 1:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_d2);
                            break;
                        case 2:
                            this.musicIsTrue = MediaPlayer.create(this, R.raw.true_d3);
                        default:
                            break;

                    }
                    DataBasemanager.level++;
                    Log.d("Level", String.valueOf(DataBasemanager.level));
                    SetAnimationCorect setAnimation = new SetAnimationCorect(rd);
                    handlerClick.postDelayed(setAnimation, 2000);
                    delay();
                } else {
                    setFalse();
                }
                return;
            case R.id.btnstop:
//                openNext(this.mHandler);
                if(!isClickAns){
                    DataBasemanager.level=1;
                    stopPlaying();
                    this.runningThread=false;
                }

                return;
            case R.id.btncall:
                if (!isClickAns) {
                    btnx.setVisibility(View.VISIBLE);
                    CallDialog callDialog = new CallDialog(this);
                    callDialog.setAnTrue(this.curentQuestion.getAn());
                    callDialog.show();
                    btncall.setEnabled(false);
                    MainActivity.IS_CALLED = true;
                }
                return;
            case R.id.btnAudience:
                if (!MainActivity.IS_AUDIENCE) {
                    btnxAudience.setVisibility(View.VISIBLE);
                    SupportAudition supportAudition=new SupportAudition(this,this.curentQuestion.getAn());
                    supportAudition.show();
                    supportAudition.setCancelable(true);
                    btnAudience.setEnabled(false);
                    MainActivity.IS_AUDIENCE = true;
                }
                return;

            case R.id.btnpercen50:
                if (!MainActivity.IS_5050) {
                    if (!isClickAns) {
                        music5050 = MediaPlayer.create(this, R.raw.sound5050);
                        music5050.start();
                        handlerClick.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                support50();
                            }
                        }, 3100);

                        btnx5050.setVisibility(View.VISIBLE);
                        MainActivity.IS_5050 = true;
                        btnpercen50.setEnabled(false);
                    }
                }
                return;
            case R.id.btnrestart:
                if (!MainActivity.IS_RESET) {
                    if (!isClickAns) {
                        btnxReset.setVisibility(View.VISIBLE);
                        MainActivity.IS_RESET = true;
                        reset.setEnabled(false);
                        setContent();
                    }
                }
                return;
        }

        musicSelect.release();
        music5050.release();
        return;

    }

    private void support50() {
        int an = curentQuestion.getAn();
        int deleteAn1 = randomNumber(4);
        int deleteAn2 = randomNumber(4);
        while (an == deleteAn1 || an == deleteAn2 || deleteAn1 == deleteAn2) {
            deleteAn1 = randomNumber(4);
            deleteAn2 = randomNumber(4);
        }
        deleteText(deleteAn1);
        deleteText(deleteAn2);

    }

    private void deleteText(int idAn) {
        switch (idAn) {
            case ANSWER_A:
                ra.setText("");
                break;
            case ANSWER_B:
                rb.setText("");
                break;
            case ANSWER_C:
                rc.setText("");
                break;
            case ANSWER_D:
                rd.setText("");
                break;
            default:
                break;
        }

    }


    @Override
    protected void onDestroy() {
        this.runningThread = false;
        this.musicSelect = null;
        this.musicIsTrue = null;
        this.musicQuestion = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        this.runningThread = false;
        super.onBackPressed();
    }


    @Override
    protected void onResume() {
        this.runningThread = true;
        super.onResume();
    }

    private void delay() {//Mở một actitivity khác.

        new CountDownTimer(4000, 1000) {//CountDownTimer đếm thời gian
            @Override
            public void onTick(long l) {//Trong khi đếm
                SystemClock.sleep(2000);
                musicIsTrue.start();
            }

            @Override
            public void onFinish() {//khi đếm kết thúc
//                DataBasemanager.level=1;
                Intent intent = new Intent();
                intent.setClass(PlayActivity.this, LevelAcitivity.class);
                intent.putExtra("currentquestion", DataBasemanager.level);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    private void setFalse() {
        //hightScoreList = dataBasemanager.getHightscore();
        DataBasemanager.level=1;
        int an = this.curentQuestion.getAn();
        SetAnimationFalse setAnimationFalse;
        Handler handler = new Handler();
        switch (an) {
            case PlayActivity.ANSWER_A:
                this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_a);
                setAnimationFalse = new SetAnimationFalse(this.ra);
                handler.postDelayed(setAnimationFalse, 3000);
                switch (randomNumber(2)) {
                    case 1:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_a);
                        break;
                    case 2:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_a2);
                        break;
                }
                break;
            case PlayActivity.ANSWER_B:

                setAnimationFalse = new SetAnimationFalse(this.rb);
                handler.postDelayed(setAnimationFalse, 3000);
                switch (randomNumber(2)) {
                    case 1:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_b);
                        break;
                    case 2:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_b2);
                        break;

                }
                break;

            case PlayActivity.ANSWER_C:

                setAnimationFalse = new SetAnimationFalse(this.rc);
                handler.postDelayed(setAnimationFalse, 3000);
                switch (randomNumber(2)) {
                    case 1:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_c);
                        break;
                    case 2:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_c2);
                        break;
                }
                break;
            case PlayActivity.ANSWER_D:

                setAnimationFalse = new SetAnimationFalse(this.rd);
                handler.postDelayed(setAnimationFalse, 3000);
                switch (randomNumber(2)) {
                    case 1:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_d);
                        break;
                    case 2:
                        this.musicIsTrue = MediaPlayer.create(this, R.raw.lose_d2);
                        break;
                }
                break;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PlayActivity.this.musicIsTrue.start();
            }
        }, 3000);
        openNext(handler);
    }
    private void stopPlaying(){
       // DataBasemanager.level=1;
        if (hightScoreList.size() != 0) {
            for (HightScore hightScore : hightScoreList) {
                if (hightScore.getMoney() < MainActivity.money) {
//                    MainActivity.id = hightScore.getId();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                        }
//                    }, 3100);
                    isHightscore = true;
                    return;
                }
            }
        }

//
        Intent intent = new Intent();
        intent.putExtra("updatehightscore",isHightscore);
        intent.setClass(PlayActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openNext(Handler handler) {
        //boolean isOpenDiaglog = false;
        if (hightScoreList.size() != 0) {
            for (HightScore hightScore : hightScoreList) {
                if (hightScore.getMoney() < MainActivity.money) {
//                    MainActivity.id = hightScore.getId();
//                    handler.postDelayed(nfw Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    }, 3100);
                    isHightscore = true;
                    return;
                }
            }
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //
                // DataBasemanager.level=1;
                Intent intent = new Intent();
               // intent.putExtra("Hight_score", MainActivity.money);
                intent.setClass(PlayActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 7100);

    }


    private class SetAnimationCorect implements Runnable {//sét nhấp nháy cho đáp án nếu trả lời đúng
        private Button btn;

        public SetAnimationCorect(Button btn) {
            this.btn = btn;
        }

        @Override
        public void run() {
            btn.setBackgroundResource(R.drawable.true_bg);
            ((AnimationDrawable) btn.getBackground()).start();
        }
    }

    private class SetAnimationFalse implements Runnable {
        private Button btnTrue;
        public SetAnimationFalse(Button btn) {
            this.btnTrue = btn;
        }

        @Override
        public void run() {
            btnTrue.setBackgroundResource(R.drawable.true_bg);
            ((AnimationDrawable) btnTrue.getBackground()).start();
        }
    }


}