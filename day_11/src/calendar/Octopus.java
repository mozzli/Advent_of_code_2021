package calendar;

public class Octopus {
    int octoValue;
    int octoPosition;
    boolean flashed = false;
    public Octopus(int octoValue, int octoPosition){
        this.octoValue = octoValue;
        this.octoPosition = octoPosition;
    }

    public void addValue(){
        if (!flashed){
            octoValue++;
        }

    }

    public void newDay(){
        flashed = false;
        octoValue++;
    }

    public boolean octoFlash(){
        if (octoValue>9){
            octoValue= 0;
            flashed = true;
            return true;
        }
        return false;
    }



}
