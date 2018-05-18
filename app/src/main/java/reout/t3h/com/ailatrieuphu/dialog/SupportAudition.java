package reout.t3h.com.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.Window;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import reout.t3h.com.ailatrieuphu.R;

public class SupportAudition extends Dialog{
    private BarChart barChart;
    private int yA, yB, yC, yD;
    private Random rd=new Random();


    public SupportAudition(@NonNull Context context,int an) {
        super(context);
        initView();
        getSupport(an);
        inisData();

    }
    public void initView(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chartbar);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        barChart = findViewById(R.id.simplebar);
    }
    private void getSupport(int answer){
        switch (answer){
            case 1:
                yB = rd.nextInt(20);
                yC = rd.nextInt(20);
                yD = rd.nextInt(20);
                yA = rd.nextInt(100-yB-yC-yD);
                break;
            case 2:
                //random();
                yA = rd.nextInt(20);
                yC = rd.nextInt(20);
                yD = rd.nextInt(20);
                yB = rd.nextInt(100-yA-yC-yD);
                break;
            case 3:

                yA = rd.nextInt(20);
                yB = rd.nextInt(20);
                yD = rd.nextInt(20);
                yC = rd.nextInt(100-yB-yA-yD);
                break;
            case 4:
                yA = rd.nextInt(20);
                yC = rd.nextInt(20);
                yB = rd.nextInt(20);
                yD = rd.nextInt(100-yB-yC-yA);
                break;

        }

    }
    private void inisData() {
        List<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(0f, yA));
        barEntries.add(new BarEntry(1f, yB));
        barEntries.add(new BarEntry(2f, yC));
        barEntries.add(new BarEntry(3f, yD));

        BarDataSet barDataSet = new BarDataSet(barEntries, "");

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);
    }
}
