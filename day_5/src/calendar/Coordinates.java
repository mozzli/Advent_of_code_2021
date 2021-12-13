package calendar;

public class Coordinates {
    int column;
    int row;
    int value = 0;

    public Coordinates(int column, int row){
        this.column = column;
        this.row = row;
    }

    public int getValue(){
        return value;
    }

    public void addValue(){
        value++;
    }
}
