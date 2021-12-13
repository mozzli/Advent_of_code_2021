package calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textManager {
    public static ArrayList<String> getLines(String source) {
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

    public static ArrayList<ArrayList<String>> getNumbers(ArrayList<String> lines){
        ArrayList<ArrayList<String>> allLines = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        Pattern liner = Pattern.compile("\\S*\\S");
        for (String line : lines) {
            Matcher m = liner.matcher(line);
            while (m.find()) {
                numbers.add(m.group());
            };
            allLines.add(new ArrayList<String>(numbers));
            numbers.clear();
        }
        return allLines;
    }
}
