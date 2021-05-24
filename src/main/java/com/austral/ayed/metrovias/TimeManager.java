package main.java.com.austral.ayed.metrovias;

public class TimeManager {

    private int time;

    public int incrementTimeIn(final int timePeriod) {
        time = time + timePeriod;
        return time;
    }

    public int getTime() {
        return time;
    }
}
