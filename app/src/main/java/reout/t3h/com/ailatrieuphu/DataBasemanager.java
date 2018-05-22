package reout.t3h.com.ailatrieuphu;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBasemanager {
    private static final String TAG = DataBasemanager.class.getSimpleName();
    private static final String DB_NAME = "Question";
    private final String pathDb;
    public static int level = 1;

    private SQLiteDatabase sqliteManager;
    Context context;

    public DataBasemanager(Context context) {
        this.context = context;
        pathDb = Environment.getDataDirectory()
                + File.separator + "data"
                + File.separator + context.getPackageName()
                + File.separator + "database"
                + File.separator + DB_NAME;
    }

    public void copyDb() {
        String path = Environment.getDataDirectory() + File.separator + "data"
                + File.separator + context.getPackageName()
                + File.separator + "database";
        new File(path).mkdir();//Creates the directory named by this abstract pathname
        File file = new File(pathDb);
        if (file.exists()) {
            return;
        }
        AssetManager manager = context.getAssets();
        try {
            InputStream in = manager.open(DB_NAME);
            OutputStream out = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int le = in.read(b);
            while (le > -1) {
                out.write(b, 0, le);
                le = in.read(b);
            }
            in.close();
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openDb() {
        copyDb();
        if (sqliteManager == null || !sqliteManager.isOpen()) {
            sqliteManager = SQLiteDatabase.openDatabase(pathDb, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDb() {
        if (sqliteManager.isOpen() && sqliteManager != null) {
            sqliteManager.close();
        }
    }
    public List<HightScore> getHightscore() {
        openDb();
        List<HightScore> hightScores = new ArrayList<>();
        Cursor c = sqliteManager.rawQuery("SELECT * FROM hight_score", null);
        if (c == null) {
            return null;
        }


        int indexId = c.getColumnIndex("id");
        int indexName = c.getColumnIndex("name");
        int indexpass = c.getColumnIndex("pass");
        int indexMoney = c.getColumnIndex("money");
        int indexTime = c.getColumnIndex("time");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(indexId);
            int questionPass = c.getInt(indexpass);
            String name = c.getString(indexName);
            int money = c.getInt(indexMoney);
            String time = c.getString(indexTime);

            HightScore hightScore = new HightScore();
            hightScore.setId(id);
            hightScore.setName(name);
            hightScore.setQuestion_pass(questionPass);
            hightScore.setMoney(money);
            hightScore.setTime(time);

            hightScores.add(hightScore);
            c.moveToNext();

        }
        c.close();
        closeDb();

        return hightScores;
    }

    public Question getQuestion() {
        openDb();
        Cursor c = this.sqliteManager.rawQuery("SELECT * FROM QUESTION" + String.valueOf(level) +
                " ORDER BY RANDOM() LIMIT 1", null);
        if (c == null) {
            return null;
        }
        c.moveToFirst();
        int indexQuestion = c.getColumnIndex(DB_NAME);
        int indexCaseA = c.getColumnIndex("CaseA");
        int indexCaseB = c.getColumnIndex("CaseB");
        int indexCaseC = c.getColumnIndex("CaseC");
        int indexCaseD = c.getColumnIndex("CaseD");
        int indexTrueCase = c.getColumnIndex("TrueCase");
        c.moveToFirst();
        String ask = c.getString(indexQuestion);
        String caseA = c.getString(indexCaseA);
        String caseB = c.getString(indexCaseB);
        String caseC = c.getString(indexCaseC);
        String caseD = c.getString(indexCaseD);
        int trueCase = c.getInt(indexTrueCase);
        closeDb();
        return new Question(ask, caseA, caseB, caseC, caseD, trueCase);
    }

    public void insertHightscore(HightScore hightScore) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", hightScore.getName());
        contentValues.put("pass", hightScore.getQuestion_pass());
        contentValues.put("money", hightScore.getMoney());
        openDb();

        long index = sqliteManager.insert("hight_score", null, contentValues);
        if (index > 0) {
            Log.d(TAG, "SUCCESS!!");
            closeDb();
        }

    }
    public void updateHightscore(int id, String name,int questionPass, int money){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name", name);
        contentValues.put("pass", questionPass);
        contentValues.put("money", money);
        openDb();
        sqliteManager.update("hight_score",contentValues,"id=?",new String[] {id+""});
    }


    //public HightScore findIdUpdate(int)
}
