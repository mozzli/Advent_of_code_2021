import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
}
