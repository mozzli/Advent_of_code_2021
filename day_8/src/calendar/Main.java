package calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static int getSomeNumbers(ArrayList<ArrayList<String>> lines) {
        ArrayList<Integer> specialNumbers = new ArrayList<>(Arrays.asList(2, 4, 3, 7));
        int numbers = 0;
        for (ArrayList<String> line : lines) {
            System.out.println(line.size());
            for (int i = 14; i >= 11; i--) {
                if (specialNumbers.contains(line.get(i).length())) {
                    numbers++;
                }
            }
        }
        return numbers;
    }

    public static int getAllNumbers(ArrayList<ArrayList<String>> lines) {
        int sum = 0;
        CodeBreaker codeBreaker;
        ArrayList<String> codeKey = new ArrayList<>();
        ArrayList<String> code = new ArrayList<>();
        for (ArrayList<String> line : lines) {
            for (int i = 0; i <= 9; i++) {
                codeKey.add(line.get(i));
            }
            for (int i = 11; i <= 14; i++) {
                code.add(line.get(i));
            }
            codeBreaker = new CodeBreaker(codeKey, code);
            sum += codeBreaker.getValue();
            codeKey.clear();
            code.clear();
        }
        return sum;

    }

    public static void main(String[] args) {
        System.out.println(getAllNumbers(textManager.getNumbers(textManager.getLines("src/resources/day_8"))));
    }
}
