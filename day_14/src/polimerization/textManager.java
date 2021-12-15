package polimerization;

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

    public static ArrayList<ArrayList<String>> getTextLines(ArrayList<String> lines) {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();
        Pattern liner = Pattern.compile("[A-Z]+");
        for (String line : lines) {
            Matcher m = liner.matcher(line);
            ArrayList<String> coordinates = new ArrayList<>();
            while (m.find()) {
                coordinates.add(m.group());
            }
            listOfLists.add(coordinates);
        }
        return listOfLists;
    }

    public static ArrayList<String> getListOfLetters(ArrayList<String> lines) {
        ArrayList<String> listOfLists = new ArrayList<>();
        Pattern liner = Pattern.compile("[A-Z]");
        ArrayList<String> coordinates = new ArrayList<>();
        for (String line : lines) {
            Matcher m = liner.matcher(line);
            while (m.find()) {
                coordinates.add(m.group());
            }
        }
        return coordinates;
    }

    public static ArrayList<String> getStringListOfLetters(String line) {
        ArrayList<String> listOfStrings = new ArrayList<>();
        Pattern liner = Pattern.compile("[A-Z]");
        Matcher m = liner.matcher(line);
        while (m.find()) {
            listOfStrings.add(m.group());
        }
        return listOfStrings;
    }
}

