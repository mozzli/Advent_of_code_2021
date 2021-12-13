package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("src/com/company/resources/data"));
        ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
        String line;

        while ((line = text.readLine()) != null) {
            listOfIntegers.add(Integer.parseInt(line));
        }

        int number = listOfIntegers.get(0);
        int counter = 0;
        for (int i = 0; i < listOfIntegers.size(); i++
        ) {
            if (listOfIntegers.get(i) > number) {
                counter++;
            }
            number = listOfIntegers.get(i);
        }
        System.out.println(counter);
        System.out.println("------------------");

        int firstNumber = (listOfIntegers.get(0) + listOfIntegers.get(1) + listOfIntegers.get(2));
        int secondNumber;
        int secondCounter = 0;
        for (int i = 0; i + 2 < listOfIntegers.size(); i++) {
            secondNumber = (listOfIntegers.get(i) + listOfIntegers.get(i + 1) + listOfIntegers.get(i + 2));
            if (secondNumber > firstNumber) {
                secondCounter++;
            }
            firstNumber = secondNumber;
        }
        System.out.println(secondCounter);
    }
}
