package calendar;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Octopus> createOctoList(ArrayList<Integer> octoValue) {
        ArrayList<Octopus> octoList = new ArrayList<>();
        for (int number = 0; number < octoValue.size(); number++) {
            octoList.add(new Octopus(octoValue.get(number), number + 1));
        }
        return octoList;
    }

    public static int flashingStarts(ArrayList<Octopus> octoList) {
        int value = 0;
        ArrayList<Octopus> octopusList = new ArrayList<>(octoList);
        first:
        for (int day = 1; day <= 500; day++) {
            for (Octopus octopus : octopusList) {
                octopus.newDay();
            }
            System.out.println(octopusList.get(1).octoValue);
            boolean allflashed = false;
            int valuer = 0;
            while (!allflashed) {
                boolean flashed = false;
                for (Octopus octopuser : octopusList) {
//                    System.out.println(octopus.octoValue);
                    if (octopuser.octoFlash()) {
                        value ++;
                        valuer ++;
                        if (octopuser.octoPosition > 10) {
                            octopusList.get(octopuser.octoPosition - 10 - 1).addValue();

                            flashed=true;
                            if (octopuser.octoPosition % 10 != 0){
                                octopusList.get(octopuser.octoPosition - 10).addValue();
                            }
                            if (octopuser.octoPosition % 10 != 1){
                                octopusList.get(octopuser.octoPosition - 10 - 2).addValue();
                            }
                        }
                        if (octopuser.octoPosition < 91) {
                            octopusList.get(octopuser.octoPosition + 10 - 1).addValue();
                            flashed=true;
                            if (octopuser.octoPosition % 10 != 0){
                                octopusList.get(octopuser.octoPosition + 10).addValue();
                            }
                            if (octopuser.octoPosition % 10 != 1 ){
                                octopusList.get(octopuser.octoPosition + 10 - 1 - 1).addValue();
                            }
                        }
                        if (octopuser.octoPosition % 10 != 0) {
                            octopusList.get(octopuser.octoPosition + 1 - 1).addValue();
                            flashed=true;
                        }
                        if (octopuser.octoPosition % 10 != 1) {
                            octopusList.get(octopuser.octoPosition - 1 - 1).addValue();
                            flashed=true;
                        }
                    }
                }
                if (valuer == 100){
                    System.out.println(day);
                    break first;
                }
                if (!flashed){
                    allflashed = true;
                }
            }
        }
        return value;

    }


    public static void main(String[] args) {
        ArrayList<Integer> input = textManager.getIntList("src/resources/day_11");
        System.out.println(flashingStarts(createOctoList(input)));
    }
}
