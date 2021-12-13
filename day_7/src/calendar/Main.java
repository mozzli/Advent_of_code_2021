package calendar;

import java.util.ArrayList;

public class Main {

    public static long lowestFuelNumber(ArrayList<Integer> crabPosition){
        int max = 0;
        double crabSum = 0.0;
        for (Integer crab:crabPosition){
            crabSum += crab;
            if (max<crab){
                max = crab;
            }
        }
        long lowestFuel = 100000000;
        int lowestPosition = 100000;

        for (int position = 0; position<=max; position++) {
            long positionFuel = 0;
            for (Integer crab:crabPosition){
                for (int i = 0; i<Math.abs(position - crab);i++){
                    positionFuel+= i;
                }
                positionFuel+= Math.abs(position - crab );
            }
            if (positionFuel<lowestFuel){
                lowestFuel = positionFuel;
                lowestPosition = position;
                
            }
            positionFuel=0;
        }
        System.out.println(crabSum/crabPosition.size());
        System.out.println(lowestPosition);
        return lowestFuel;
    }

    public static void main(String[] args) {
        ArrayList<Integer> crabsPosition = textManager.getNumbers(textManager.getLines("src/resources/day_7_2"));
        System.out.println(lowestFuelNumber(crabsPosition));
    }
}
