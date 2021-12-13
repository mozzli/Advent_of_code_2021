package calendar;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Table table = new Table();
        System.out.println(table.getTable().size());
        ArrayList<String> listOfLines = textManager.getLines("src/calendar/resources/day_5");
        table.updateCoordinates(listOfLines,true);
        System.out.println(table.getTableValue());
        table.showMap();
    }


}
