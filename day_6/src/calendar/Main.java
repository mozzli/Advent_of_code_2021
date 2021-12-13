package calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static long fishCounter(int days) {
        ArrayList<LanternFish> newFishes = new ArrayList<>();
        ArrayList<LanternFish> fishes = new ArrayList<>(Collections.singletonList(new LanternFish()));
        for (int day = 0; day <= days; day++) {
            for (LanternFish fish : fishes) {
                if (fish.getDay() == 0) {
                    newFishes.add(new LanternFish());
                }
                fish.fishDay();
            }
            if (day < days) {
                fishes.addAll(newFishes);
                newFishes.clear();
            }
        }
        return fishes.size();
    }

    public static long firstPattern(ArrayList<LanternFish> startingFishes, int fullDays) {
        HashMap<Integer, Long> bigNumbers = new HashMap<>();
        ArrayList<LanternFish> listOfFishes = new ArrayList<>(startingFishes);
        ArrayList<LanternFish> newFishes = new ArrayList<>();
        long fishes = 0;

        for (int i = 0; i <= 200; i++) {
            bigNumbers.put(i, fishCounter(i));
        }
        for (int day = 0; day < fullDays; day++) {
            for (LanternFish listOfFish : listOfFishes) {
                if (listOfFish.getDay() == 0) {
                    if (day < 100) {
                        newFishes.add(new LanternFish());
                    } else {
                        fishes += bigNumbers.get(fullDays - day - 1);
                        System.out.println(fishes);
                    }
                }
                listOfFish.fishDay();
            }
            listOfFishes.addAll(newFishes);
            newFishes.clear();
        }
        return listOfFishes.size() + fishes;
    }

    public static void main(String[] args) {
        ArrayList<String> lines = textManager.getLines("src/calendar/resources/day_6");
        ArrayList<String> startingFish = new ArrayList<>();
        ArrayList<LanternFish> listOfFishes = new ArrayList<>();

        Pattern liner = Pattern.compile("[\\d]*[\\d]");
        for (String line : lines) {
            Matcher m = liner.matcher(line);
            while (m.find()) {
                startingFish.add(m.group());
            }
        }

        for (String fishAge : startingFish) {
            listOfFishes.add(new LanternFish(Integer.parseInt(fishAge)));
        }

        System.out.println(firstPattern(listOfFishes, 300));
    }
}
