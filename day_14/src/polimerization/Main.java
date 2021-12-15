package polimerization;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static final String INPUT_SOURCE = "src/resources/day_14_input";
    public static final String KEY_SOURCE = "src/resources/day_14_key";
    static String inputPlaceholder = textManager.getLinesAsString(INPUT_SOURCE).get(0);
    static String input = textManager.getLinesAsString(INPUT_SOURCE).get(0);
    static String outputPlaceholder;
    static String lastCodeKey = input.substring(input.length() - 1);
    static ArrayList<String> codes = new ArrayList<>();
    static ArrayList<ArrayList<String>> key = textManager.getTextLines(textManager.getLinesAsString(KEY_SOURCE));

    public static void createCodeList() {
        for (ArrayList<String> code : key) {
            if (!codes.contains(code.get(1))) {
                codes.add(code.get(1));
            }
        }
    }

    public static String createEasyOutput(String input, HashMap<String, String> keyMap, HashMap<String, Code> codeMap) {
        StringBuilder code = new StringBuilder();
        outputPlaceholder = "";
        for (int i = 0; i < input.length() - 1; i++) {
            code.setLength(0);
            code.append(input.charAt(i)).append(input.charAt(i + 1));
            String stringCode = code.toString();
            if (keyMap.containsKey(stringCode)) {
                outputPlaceholder += codeMap.get(stringCode).getName();
            }
        }
        Main.inputPlaceholder = outputPlaceholder + lastCodeKey;
        return outputPlaceholder + lastCodeKey;
    }

    public static String createEasyOutputString(String input, HashMap<String, String> keyMap, HashMap<String, Code> codeMap) {
        StringBuilder code = new StringBuilder();
        String output = "";
        for (int i = 0; i < input.length() - 1; i++) {
            code.setLength(0);
            code.append(input.charAt(i)).append(input.charAt(i + 1));
            if (keyMap.containsKey(code.toString())) {
                output += codeMap.get(code.toString()).getName();
            }
        }
        return output;
    }

    public static HashMap<String, Code> createCodeMap(ArrayList<ArrayList<String>> list, HashMap<String, String> keymap) {
        HashMap<String, Code> codeMap = new HashMap<>();
        for (ArrayList<String> code : list) {
            codeMap.put(code.get(0), new Code(code.get(0), keymap, codes));
        }
        return codeMap;
    }

    public static HashMap<String, String> createKeyMap(ArrayList<ArrayList<String>> keys) {
        HashMap<String, String> keyMap = new HashMap<>();
        for (ArrayList<String> key : keys) {
            keyMap.put(key.get(0), key.get(1));
        }
        return keyMap;
    }

    public static HashMap<String, Long> getResult(String output) {
        HashMap<String, Long> outputMap = new HashMap<>();
        for (int i = 0; i < output.length(); i++) {
            String value = output.substring(i, i + 1);
            if (outputMap.containsKey(value)) {
                outputMap.replace(value, outputMap.get(value) + 1);
            } else {
                outputMap.put(value, 1L);
            }
        }
        for (String code : codes) {
            if (!outputMap.containsKey(code)) {
                outputMap.put(code, 0L);
            }
        }
        return outputMap;
    }

    public static ArrayList<String> breakTheCode(ArrayList<String> output, HashMap<String, String> keyMap) {
        ArrayList<String> newOutput = new ArrayList<>(output);
        for (int i = output.size() - 2; i >= 0; i--) {
            StringBuilder keyValue = new StringBuilder(output.get(i) + output.get(i + 1));
            if (keyMap.containsKey(String.valueOf(keyValue))) {
                newOutput.add(i + 1, keyMap.get(String.valueOf(keyValue)));
            }
        }
        return newOutput;
    }

    public static ArrayList<String> createOutput(ArrayList<String> input, HashMap<String, String> keyMap, int numberOfTries) {
        ArrayList<String> output = new ArrayList<>(input);
        for (int i = 1; i <= numberOfTries; i++) {
            output = breakTheCode(output, keyMap);
        }
        return output;
    }

    public static Long getFinalScore(HashMap<String, String> keyMap, HashMap<String, Code> listOfCodes, ArrayList<ArrayList<String>> key) {
        String output = null;
        long maxValue = 0L;
        long minValue = 0L;
        HashMap<String, Long> finalScore = new HashMap<>();
        for (int i = 0; i < 2; i++) {
            output = createEasyOutput(inputPlaceholder, keyMap, listOfCodes);
            System.out.println(i);
        }
        for (ArrayList<String> i : key) {
            listOfCodes.get(i.get(0)).getMore(listOfCodes);
        }
        for (String coder : codes) {
            finalScore.put(coder, 0L);
        }
        String code;
        for (int i = 0; i < output.length() - 1; i++) {
            code = output.substring(i, i + 2);
            if (keyMap.containsKey(code)) {
                finalScore = listOfCodes.get(code).getFinalScore(finalScore);
            }
        }
        finalScore.replace(lastCodeKey, finalScore.get(lastCodeKey) + 1);
        for (String codeKey : codes) {
            if (maxValue == 0L) {
                maxValue = finalScore.get(codeKey);
            } else if (maxValue < finalScore.get(codeKey)) {
                maxValue = finalScore.get(codeKey);
            }
            if (minValue == 0L) {
                minValue = finalScore.get(codeKey);
            } else if (minValue > finalScore.get(codeKey)) {
                minValue = finalScore.get(codeKey);
            }
        }
        return (maxValue - minValue);
    }

    public static void main(String[] args) {
        createCodeList();
        HashMap<String, String> keyMap = createKeyMap(key);
        HashMap<String, Code> listOfCodes = createCodeMap(key, keyMap);
        System.out.println(getFinalScore(keyMap, listOfCodes, key));
    }
}
