package reout.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import reout.t3h.com.ailatrieuphu.DataBasemanager;
import reout.t3h.com.ailatrieuphu.MainActivity;
import reout.t3h.com.ailatrieuphu.PlayActivity;
import reout.t3h.com.ailatrieuphu.R;

import static reout.t3h.com.ailatrieuphu.R.raw.help_call;

/**
 * Created by nguye on 3/5/2018.
 */

public class CallDialog extends Dialog implements View.OnClickListener {
    private ImageView btnbigronaldo;
    private ImageView congvinh;
    private ImageView messi;
    private ImageView bill;
    private ImageView suares;
    private ImageView naymar;
    private TextView an_helper;
    private MediaPlayer musicWho=null;
    private MediaPlayer musicConnect=null;
    private MediaPlayer musicRing=null;
    private int anTrue;


    public CallDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_dialog);
        init();
    }


    private void init() {
        an_helper=(TextView)findViewById(R.id.an_helper);
       this.musicWho=MediaPlayer.create(this.getContext(), R.raw.help_call);
       this.musicWho.start();
        btnbigronaldo=(ImageView)findViewById(R.id.bigronaldo);
        btnbigronaldo.setOnClickListener(this);
        messi=(ImageView)findViewById(R.id.messi);
        messi.setOnClickListener(this);
        naymar=(ImageView)findViewById(R.id.naymar);
        naymar.setOnClickListener(this);
        congvinh=(ImageView)findViewById(R.id.congvinh);
        congvinh.setOnClickListener(this);
        suares=(ImageView)findViewById(R.id.suares);

        suares.setOnClickListener(this);
        bill=(ImageView)findViewById(R.id.bill);

        bill.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


            switch (view.getId()) {
                case R.id.bigronaldo:
                    getAnHelp(anHelper(), "Ronaldo");
                    messi.setClickable(false);
                    naymar.setClickable(false);
                    congvinh.setClickable(false);
                    bill.setClickable(false);
                    suares.setClickable(false);
                    bill.setClickable(false);
                    break;
                case R.id.messi:
                    getAnHelp(anHelper(), "Messi");
                    btnbigronaldo.setClickable(false);
                    naymar.setClickable(false);
                    congvinh.setClickable(false);
                    bill.setClickable(false);
                    suares.setClickable(false);
                    messi.setClickable(false);

                    break;
                case R.id.congvinh:
                    getAnHelp(anHelper(), "Cong Vinh");
                    btnbigronaldo.setClickable(false);
                    naymar.setClickable(false);
                    messi.setClickable(false);
                    bill.setClickable(false);
                    suares.setClickable(false);
                    congvinh.setClickable(false);
                    break;
                case R.id.bill:
                    getAnHelp(anHelper(), "Bill");
                    btnbigronaldo.setClickable(false);
                    naymar.setClickable(false);
                    messi.setClickable(false);
                    congvinh.setClickable(false);
                    suares.setClickable(false);
                    bill.setClickable(false);
                    break;
                case R.id.suares:
                    getAnHelp(anHelper(), "Suares");
                    btnbigronaldo.setClickable(false);
                    naymar.setClickable(false);
                    messi.setClickable(false);
                    bill.setClickable(false);
                    congvinh.setClickable(false);
                    suares.setClickable(false);
                    break;
                case R.id.naymar:
                    getAnHelp(anHelper(), "Naymar");
                    btnbigronaldo.setClickable(false);
                    congvinh.setClickable(false);
                    messi.setClickable(false);
                    bill.setClickable(false);
                    suares.setClickable(false);
                    naymar.setClickable(false);
                    break;
            }
            musicWho.release();



    }
    private int anHelper(){
        int an=new Random().nextInt(4)+1;
        return an;
    }

    public void setAnTrue(int anTrue) {
        this.anTrue = anTrue;
    }

    private void getAnHelp(int idAn, String nameHelper){
        switch (this.anTrue){
            case 1:
                an_helper.setText("Dap an cua" +nameHelper+ " la: A");
                break;
            case 2:
                an_helper.setText("Dap an cua" +nameHelper+ " la: B");
                break;
            case 3:
                an_helper.setText("Dap an cua" +nameHelper+ " la: C");
                break;
            case 4:
                an_helper.setText("Dap an cua" +nameHelper+ " la: D");
                break;
        }
    }

}
