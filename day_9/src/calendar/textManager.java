package calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textManager {
    public static ArrayList<String> getLinesAsString(String source) {
        ArrayList<String> listOfStrings = new ArrayList<String>();
        try {
            BufferedReader text = new BufferedReader(new FileReader(source));
            String line;
            while ((line = text.readLine()) != null) {
                listOfStrings.add(line);
            }
            return listOfStrings;
        } catch (IOException e) {
            return listOfStrings;
        }
    }

    public static ArrayList<ArrayList<Integer>> getLinesAsInt(String source) {
        ArrayList<ArrayList<Integer>> listOfIntegers = new ArrayList<>();
        try {
            BufferedReader text = new BufferedReader(new FileReader(source));
            String line;
            while ((line = text.readLine()) != null) {
                ArrayList<Integer> numbers = new ArrayList<>();
                for (int i =0; i<line.length(); i++) {
                    numbers.add(Integer.parseInt(String.valueOf(line.charAt(i))));
                }
                listOfIntegers.add(numbers);
            }
            return listOfIntegers;
        } catch (IOException e) {
            return listOfIntegers;
        }
    }

    public static ArrayList<Integer> getNumbers(ArrayList<String> lines){
        ArrayList<Integer> numbers = new ArrayList<>();
        Pattern liner = Pattern.compile("[\\d]*[\\d]");
        for (String line : lines) {
            Matcher m = liner.matcher(line);
            while (m.find()) {
                numbers.add(Integer.parseInt(m.group()));
            }
        }
        return numbers;
    }
}
