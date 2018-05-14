package reout.t3h.com.ailatrieuphu;

/**
 * Created by nguye on 3/9/2018.
 */

public class HightScore {
    private int id;
    private String name;
    private int question_pass;
    private int money;
    private String time;

    public HightScore(int id, String name, int question_pass, int money, String time) {
        this.id = id;
        this.name = name;
        this.question_pass = question_pass;
        this.money = money;
        this.time = time;
    }

    public HightScore(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestion_pass() {
        return question_pass;
    }

    public void setQuestion_pass(int question_pass) {
        this.question_pass = question_pass;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
