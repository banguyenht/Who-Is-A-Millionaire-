package reout.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

import reout.t3h.com.ailatrieuphu.DataBasemanager;
import reout.t3h.com.ailatrieuphu.HightScore;
import reout.t3h.com.ailatrieuphu.R;
import reout.t3h.com.ailatrieuphu.Utils;

/**
 * Created by nguye on 3/10/2018.
 */

public class ShowHightscore extends Dialog {
    private TextView tvName;
    private TextView tvPass;
    private TextView tvMoney;

    public ShowHightscore(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showhigscore_dialog);
        init();

    }
    private  void init(){
        tvName=(TextView)findViewById(R.id.name_hight);
        tvPass=(TextView)findViewById(R.id.pass);
        tvMoney=(TextView)findViewById(R.id.money);


//        DataBasemanager dataBasemanager=new DataBasemanager(this.getContext());
//        List<HightScore> hightScores=dataBasemanager.getHightscore();
//        if(hightScores.size()!=0){
//            tvName.setText(hightScores.get(0).getName());
//            tvPass.setText(String.valueOf(hightScores.get(0).getQuestion_pass()));
//            tvMoney.setText(String.valueOf(hightScores.get(0).getMoney()));
//        }
       // Utils.sortHightscore(hightScores);

    }

    @Override
    protected void onStart() {
        super.onStart();
        DataBasemanager dataBasemanager=new DataBasemanager(this.getContext());
        List<HightScore> hightScores=dataBasemanager.getHightscore();
        if(hightScores.size()!=0){
            tvName.setText(hightScores.get(0).getName());
            tvPass.setText(String.valueOf(hightScores.get(0).getQuestion_pass()));
            tvMoney.setText(String.valueOf(hightScores.get(0).getMoney()));
        }
        Utils.sortHightscore(hightScores);
    }
}
