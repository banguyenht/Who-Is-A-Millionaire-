package reout.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.PropertyPermission;

import reout.t3h.com.ailatrieuphu.DataBasemanager;
import reout.t3h.com.ailatrieuphu.HightScore;
import reout.t3h.com.ailatrieuphu.MainActivity;
import reout.t3h.com.ailatrieuphu.PlayActivity;
import reout.t3h.com.ailatrieuphu.R;
import reout.t3h.com.ailatrieuphu.Utils;

/**
 * Created by nguye on 3/9/2018.
 */

public class SaveScore extends Dialog implements View.OnClickListener {
    private Button btnSave;
    private Button btnCancel;
    private EditText edtName;
    private HightScore hightScore;
    private DataBasemanager dataBasemanager;
    public SaveScore(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hightscore_dialog);
        init();
    }

    private void init() {
        btnCancel = (Button) findViewById(R.id.btncancel);
        btnSave = (Button) findViewById(R.id.btnsave);
        edtName = (EditText) findViewById(R.id.edtname);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        edtName = (EditText) findViewById(R.id.edtname);
        dataBasemanager = new DataBasemanager(this.getContext());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnsave:
                if(dataBasemanager.getHightscore().size()!=0){
                    dataBasemanager.updateHightscore(MainActivity.id, edtName.getText().toString(),
                            MainActivity.questionPass, MainActivity.money);
                }
                else {
                    HightScore hightScore=new HightScore();
                    hightScore.setName(edtName.getText().toString());
                    hightScore.setMoney(MainActivity.money);
                    hightScore.setQuestion_pass(MainActivity.questionPass);
                    dataBasemanager.insertHightscore(hightScore);
                }
                dismiss();
                return;
        }

    }
}
