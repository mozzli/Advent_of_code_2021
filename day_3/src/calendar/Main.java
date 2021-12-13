package calendar;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static int getFirstData(ArrayList<String> listOfStrings, DataOptions dataOptions) {

        HashMap<Integer, Integer> numbersListCounter = new HashMap<Integer, Integer>();
        for (int i = 0; i < 12; i++) {
            numbersListCounter.put(i + 1, 0);
        }

        for (String numbers : listOfStrings) {
            for (int number = 0; number < numbers.length(); number++) {
                if (Character.toString(numbers.charAt(number)).equals("1")) {
                    numbersListCounter.replace(number + 1, numbersListCounter.get(number + 1) + 1);
                }
            }
        }

        String firstNumber = "0";
        String secondNumber = "1";

        if (dataOptions == DataOptions.gamma) {
            firstNumber = "1";
            secondNumber = "0";
        }

        StringBuilder data = new StringBuilder();
        for (int i = 0; i < numbersListCounter.size(); i++) {
            if (numbersListCounter.get(i + 1) > listOfStrings.size() / 2) {
                data.append(firstNumber);
            } else data.append(secondNumber);
        }
        return Integer.parseInt(data.toString(), 2);
    }

    public static int getSecondData(ArrayList<String> listOfStrings, DataOptions dataOptions) {
        String firstNumber = "1";
        String secondNumber = "0";
        if (dataOptions == DataOptions.carbonDioxide){
            firstNumber = "0";
            secondNumber = "1";
        }

        ArrayList<String> testNumbers = new ArrayList<String>(listOfStrings);
        ArrayList<String> restOfTheNumbers = new ArrayList<String>();
        int onesCounter;
        int zerosCounter;
        int i = 0;
        while (restOfTheNumbers.size() != 1) {
            restOfTheNumbers.clear();
            onesCounter = 0;
            zerosCounter = 0;

            for (String numbers : testNumbers) {
                if (Character.toString(numbers.charAt(i)).equals("1")) {
                    onesCounter++;
                } else {
                    zerosCounter++;
                }

            }
            if (onesCounter >= zerosCounter) {
                for (String numbers : testNumbers) {
                    if (Character.toString(numbers.charAt(i)).equals(firstNumber)) {
                        restOfTheNumbers.add(numbers);
                    }
                }
            } else {
                for (String number : testNumbers) {
                    if (Character.toString(number.charAt(i)).equals(secondNumber)) {
                        restOfTheNumbers.add(number);
                    }
                }
            }
            i++;
            testNumbers.clear();
            testNumbers.addAll(restOfTheNumbers);
        }
        return Integer.parseInt(restOfTheNumbers.get(0), 2);
    }

    public static void main(String[] args) {

        ArrayList<String> listOfStrings = textManager.getLines("src/resources/data3");

        int gammaNumber = getFirstData(listOfStrings, DataOptions.gamma);
        int epsilonNumber = getFirstData(listOfStrings, DataOptions.epsilon);
        int oxygenNumber = getSecondData(listOfStrings, DataOptions.oxygen);
        int carbonDioxideNumber = getSecondData(listOfStrings, DataOptions.carbonDioxide);

        System.out.println("gamma rate: " + gammaNumber);
        System.out.println("epsilon rate: " + epsilonNumber);
        System.out.println("final result: " + gammaNumber * epsilonNumber);
        System.out.println("Oxygen rate: " + oxygenNumber);
        System.out.println("Carbon Dioxide rate: " + carbonDioxideNumber);
        System.out.println("final result: " + oxygenNumber*carbonDioxideNumber);
    }
}
