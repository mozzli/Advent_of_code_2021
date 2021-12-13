package calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("src/resources/data2"));
        ArrayList<String> listOfStrings = new ArrayList<String>();
        String line;

        while ((line = text.readLine()) != null) {
            listOfStrings.add(line);
        }
        System.out.println(listOfStrings);

        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (String singleLine : listOfStrings
        ) {
            int number = Integer.parseInt(singleLine.substring(singleLine.length() - 1));
            switch (singleLine.substring(0, singleLine.length() - 2)) {
                case "forward" -> {
                    horizontal += number;
                    depth += aim * number;
                }

                case "down" -> {
                    aim += number;
                }
                case "up" -> {
                    aim -= number;
                }
            }

        }
        System.out.println("Horizontal " + horizontal);
        System.out.println("Depth: " + depth);
        System.out.println("Final case:" + (horizontal * depth));
    }
}
