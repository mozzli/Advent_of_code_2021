package calendar;

import java.util.ArrayList;
import java.util.Arrays;

public class CodeBreaker {

    private ArrayList<String> codeKey;
    private ArrayList<String> code;

    String zero;
    String one;
    String two;
    String three;
    String four;
    String five;
    String six;
    String seven;
    String eight;
    String nine;

    public CodeBreaker(ArrayList<String> codeKey, ArrayList<String> code){
        this.codeKey = codeKey;
        this.code = code;
        getBasicNumbers();
        breakCode();
        writeCode();
    }

    private void getBasicNumbers(){
        for (String cell : codeKey){
            switch (cell.length()){
                case 2 -> one = cell;
                case 3 -> seven = cell;
                case 4 -> four = cell;
                case 7 -> eight = cell;
            }
        }
    }

    private void breakCode(){
        codeKey.remove(one);
        codeKey.remove(four);
        codeKey.remove(seven);
        codeKey.remove(eight);
//        get 3
        for (String cell: codeKey){
            if (cell.length() == 5 && cell.contains(String.valueOf(one.charAt(0))) && cell.contains(String.valueOf(one.charAt(1)))){
                three = cell;
                break;
            }
        }
        codeKey.remove(three);
//        get 9
        for (String cell: codeKey){
            if (cell.length() == 6 && cell.contains(String.valueOf(three.charAt(0)))
                    && cell.contains(String.valueOf(three.charAt(1)))
                    && cell.contains(String.valueOf(three.charAt(2)))
                    && cell.contains(String.valueOf(three.charAt(3)))
                    && cell.contains(String.valueOf(three.charAt(4)))){
                nine = cell;
            }
        }
        codeKey.remove(nine);
//        get 0
        for (String cell: codeKey){
            if (cell.length() == 6 && cell.contains(String.valueOf(seven.charAt(0)))
                    && cell.contains(String.valueOf(seven.charAt(1)))
                    && cell.contains(String.valueOf(seven.charAt(2)))){
                zero = cell;
            }
        }
        codeKey.remove(zero);
//        get 6
        for (String cell: codeKey){
            if (cell.length() == 6){
                six = cell;
            }
        }
        codeKey.remove(six);
//        get 5
        for (String cell: codeKey){
            if (six.contains(String.valueOf(cell.charAt(0)))
                    && six.contains(String.valueOf(cell.charAt(1)))
                    && six.contains(String.valueOf(cell.charAt(2)))
                    && six.contains(String.valueOf(cell.charAt(3)))
                    && six.contains(String.valueOf(cell.charAt(4)))){
                five = cell;
            }
        }
        codeKey.remove(five);
        two = codeKey.get(0);
    }

    private String sortCode(String code){
        char[] charArray = code.toCharArray();
        Arrays.sort(charArray);
        return Arrays.toString(charArray);
    }

    public int getValue(){
        zero = sortCode(zero);
        one = sortCode(one);
        two = sortCode(two);
        three = sortCode(three);
        four = sortCode(four);
        five = sortCode(five);
        six = sortCode(six);
        seven = sortCode(seven);
        eight = sortCode(eight);
        nine = sortCode(nine);
        int sum = 0;
        StringBuilder numberString = new StringBuilder();
        for (String number: code){
            if (sortCode(number).equals(zero)){numberString.append("0");}
            else if (sortCode(number).equals(one)){numberString.append("1");}
            else if (sortCode(number).equals(two)){numberString.append("2");}
            else if (sortCode(number).equals(three)){numberString.append("3");}
            else if (sortCode(number).equals(four)){numberString.append("4");}
            else if (sortCode(number).equals(five)){numberString.append("5");}
            else if (sortCode(number).equals(six)){numberString.append("6");}
            else if (sortCode(number).equals(seven)){numberString.append("7");}
            else if (sortCode(number).equals(eight)){numberString.append("8");}
            else if (sortCode(number).equals(nine)){numberString.append("9");}
        }
        return Integer.parseInt(String.valueOf(numberString));
    }

    private void writeCode(){
        String firstRow = " "+" "+"."+"."+"."+" "+" "+" "+" "+" "+" "+"."+".","."," "," "," "," "," "," ",".",".","."," "," "};
        ArrayList<String> secondRow = new ArrayList<>(Arrays.asList("."," "," "," "," "," ","."," "," ","."," ",".",".","."," ","."," "," ","."," "," "," "," "," ","."));
        ArrayList<String> thirdRow = new ArrayList<>(Arrays.asList("."," "," "," "," "," ","."," "," ","."," "," "," "," "," ","."," "," ","."," "," "," "," "," ","."));
        ArrayList<String> forthRow = new ArrayList<>(Arrays.asList(" "," ",".",".","."," "," "," "," "," "," ",".",".","."," "," "," "," "," "," ",".",".","."," "," "));
        ArrayList<String> fifthRow = new ArrayList<>(Arrays.asList("."," "," "," "," "," ","."," "," ","."," "," "," "," "," ","."," "," "," "," "," "," "," "," ","."));
        ArrayList<String> sixthRow = new ArrayList<>(Arrays.asList("."," "," "," "," "," ","."," "," ","."," "," "," "," "," ","."," "," "," "," "," "," "," "," ","."));
        ArrayList<String> seventhRow = new ArrayList<>(Arrays.asList(" "," ",".",".","."," "," "," "," "," "," ",".",".","."," "," "," "," "," "," ",".",".","."," "," "));


        String blankTable = """
                    . . .      . . .      . . .  \s
                  .       .  .       .  .       .\s
                  .       .  .       .  .       .\s
                    . . .      . . .      . . .  \s
                  .       .  .       .  .       .\s
                  .       .  .       .  .       .\s
                    . . .      . . .      . . .  """;

        blankTable.replace(".", "a");

        System.out.println(Arrays.toString(firstRow));
    }

}
