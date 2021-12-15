package polimerization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Code {
    ArrayList<String> name;
    String stringName;
    String placeholderString;
    ArrayList<String> allKeys;
    HashMap<String, String> keyMap;
    HashMap<String,Long> twentyScore = new HashMap<>();
    StringBuilder tenName;

    public Code(String name, HashMap<String, String> keyMap, ArrayList<String> allKeys) {
        stringName = name;
        this.name = getList(name);
        this.keyMap = keyMap;
        ArrayList<String> listOfOutput = Main.createOutput(this.name, keyMap, 10);
        this.allKeys = allKeys;
        tenName = new StringBuilder("");
        for (String character : listOfOutput) {
            tenName.append(character);
        }
        stringName = tenName.deleteCharAt(tenName.length() - 1).toString();
        System.out.println("------");

    }

    private ArrayList<String> getList(String name) {
        return textManager.getListOfLetters(new ArrayList<>(Arrays.asList(name, " ")));
    }

    public String getName() {
        return stringName;
    }

    public void getMore(HashMap<String,Code>codeMap){
        placeholderString = Main.createEasyOutputString(stringName + name.get(1),keyMap,codeMap);
        twentyScore = Main.getResult(placeholderString);
        placeholderString = "";
        System.out.println("++++++++");
    }

    public HashMap<String, Long> getFinalScore(HashMap<String,Long> currentScore){
        for (String code: Main.codes){
            currentScore.replace(code, currentScore.get(code)+twentyScore.get(code));
        }
        return currentScore;
    }

}
