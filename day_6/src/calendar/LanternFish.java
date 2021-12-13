package calendar;

public class LanternFish {

    int day;

    public LanternFish(int day){
        this.day = day;
    }

    public LanternFish(){
        this.day = 8;
    }

    public void fishDay(){
        if (day>0){
            day-=1;
        }else{
            day = 6;
        }
    }

    public int getDay(){
        return day;
    }

}
