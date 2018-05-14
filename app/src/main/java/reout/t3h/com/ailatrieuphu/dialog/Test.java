package reout.t3h.com.ailatrieuphu.dialog;

import android.app.Application;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import reout.t3h.com.ailatrieuphu.HightScore;
import reout.t3h.com.ailatrieuphu.PlayActivity;
import reout.t3h.com.ailatrieuphu.R;

/**
 * Created by nguye on 3/11/2018.
 */

public class Test extends DialogFragment implements View.OnClickListener{
    private Button btnSave;
    private Button btnCancel;
    private EditText edtName;
    private HightScore hightScore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.hightscore_dialog,container,false);
        init(view);
        return view;
    }

    private void init(View view){
        btnCancel=(Button)view.findViewById(R.id.btncancel);
        btnSave=(Button)view.findViewById(R.id.btnsave);
        edtName=(EditText) view.findViewById(R.id.edtname);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        edtName=(EditText)view.findViewById(R.id.edtname);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsave:
               Toast.makeText(getActivity(),"save",Toast.LENGTH_LONG).show();
               return;
        }

    }
}
