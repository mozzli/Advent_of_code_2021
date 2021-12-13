package calendar;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static Long getMiddleIncompleteValue(ArrayList<ArrayList<Character>> input) {
        ArrayList<Long> allNumbers = new ArrayList<>();
        ArrayList<ArrayList<Character>> lines = new ArrayList<>(input);
        for (ArrayList<Character> line : lines) {
            boolean endLoop = false;
            int charNumber = 0;
            boolean corrupted = false;
            while (!endLoop){
                boolean itsNotAll = false;
                switch (line.get(charNumber).toString()) {
                    case "[":
                        switch (line.get(charNumber + 1).toString()) {
                            case "]" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case  ")", ">", "}" -> {
                                corrupted = true;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                    case "(":
                        switch (line.get(charNumber + 1).toString()) {
                            case ")" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "]", ">", "}" -> {
                                corrupted = true;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                    case "<":
                        switch (line.get(charNumber + 1).toString()) {
                            case ">" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case ")", "]", "}" -> {
                                corrupted = true;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                    case "{":
                        switch (line.get(charNumber + 1).toString()) {
                            case "}" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "]", ">", ")" -> {
                                corrupted = true;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                }
                if (!itsNotAll && charNumber == line.size() -2){
                    endLoop = true;
                }else if (!itsNotAll){
                    charNumber++;
                }else {
                    charNumber = 0;
                }
            }
            if (!corrupted){
                ArrayList<Integer> numbers = new ArrayList<>();
                for (int i = line.size()-1; i>=0; i--){
                    switch (line.get(i).toString()){
                        case "(" -> numbers.add(1);
                        case "[" -> numbers.add(2);
                        case "{" -> numbers.add(3);
                        case "<" -> numbers.add(4);
                    }
                }
                long score = 0;
                for (int i = 0; i<numbers.size();i++){
                    score = (score*5) + numbers.get(i);
                }
                allNumbers.add(score);
            }

        }
        Collections.sort(allNumbers);
        return allNumbers.get((allNumbers.size()/2));
    }

    public static Integer getErrorValue(ArrayList<ArrayList<Character>> input) {
        int value = 0;
        ArrayList<ArrayList<Character>> lines = new ArrayList<>(input);
        for (ArrayList<Character> line : lines) {
            boolean endLoop = false;
            int charNumber = 0;
            while (!endLoop){
                boolean itsNotAll = false;
                switch (line.get(charNumber).toString()) {
                    case "[":
                        switch (line.get(charNumber + 1).toString()) {
                            case "]" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case ")" -> {
                                value += 3;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case ">" -> {
                                value += 25137;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "}" -> {
                                value += 1197;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                    case "(":
                        switch (line.get(charNumber + 1).toString()) {
                            case ")" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "]" -> {
                                value += 57;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case ">" -> {
                                value += 25137;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "}" -> {
                                value += 1197;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                    case "<":
                        switch (line.get(charNumber + 1).toString()) {
                            case ">" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case ")" -> {
                                value += 3;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "]" -> {
                                value += 57;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "}" -> {
                                value += 1197;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                    case "{":
                        switch (line.get(charNumber + 1).toString()) {
                            case "}" -> {
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case ")" -> {
                                value += 3;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case ">" -> {
                                value += 25137;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                            case "]" -> {
                                value += 57;
                                line.remove(charNumber + 1);
                                line.remove(charNumber);
                                itsNotAll = true;
                            }
                        }
                        break;
                }
                if (!itsNotAll && charNumber == line.size() -2){
                    endLoop = true;
                }else if (!itsNotAll){
                    charNumber++;
                }else {
                    charNumber = 0;
                }
            }
        }
        return value;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> input = textManager.getList("src/resources/day_10");
        System.out.println(getErrorValue(input));
        System.out.println(getMiddleIncompleteValue(input));
    }
}
