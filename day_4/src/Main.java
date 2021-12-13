import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static ArrayList<HashMap<Integer, HashMap<Integer, Boolean>>> createTables(ArrayList<String> tables) {
        ArrayList<HashMap<Integer, HashMap<Integer, Boolean>>> allTables = new ArrayList<>();
        for (int i = 0; i < tables.size() / 6; i++) {
            HashMap<Integer, HashMap<Integer, Boolean>> table = new HashMap<>();
            for (int row = 0; row < 5; row++) {
                for (int column = 0; column < 5; column++) {
                    HashMap<Integer, Boolean> cell = new HashMap<>();
                    String number = tables.get(i * 6 + row).substring(column * 3, column * 3 + 2);
                    cell.put(Integer.parseInt(number.trim()), false);
                    table.put(row * 5 + (column + 1), cell);
                }
                allTables.add(table);
            }
        }

        return allTables;
    }

    public static boolean checkWin(HashMap<Integer, HashMap<Integer, Boolean>> table) {
        ArrayList<Boolean> check = new ArrayList<>();
        ArrayList<ArrayList<Integer>> win = new ArrayList<>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)),
                new ArrayList<Integer>(Arrays.asList(6, 7, 8, 9, 10)),
                new ArrayList<Integer>(Arrays.asList(11, 12, 13, 14, 15)),
                new ArrayList<Integer>(Arrays.asList(16, 17, 18, 19, 20)),
                new ArrayList<Integer>(Arrays.asList(21, 22, 23, 24, 25)),
                new ArrayList<Integer>(Arrays.asList(1, 6, 11, 16, 21)),
                new ArrayList<Integer>(Arrays.asList(2, 7, 12, 17, 22)),
                new ArrayList<Integer>(Arrays.asList(3, 8, 13, 18, 23)),
                new ArrayList<Integer>(Arrays.asList(4, 9, 14, 19, 24)),
                new ArrayList<Integer>(Arrays.asList(5, 10, 15, 20, 25))));
//                new ArrayList<Integer>(Arrays.asList(1, 7, 13, 19, 25)),
//                new ArrayList<Integer>(Arrays.asList(5, 9, 13, 17, 21))));
        for (ArrayList<Integer> winCondition : win) {
            for (int cell : winCondition) {
                if (table.get(cell).containsValue(true)) {
                    check.add(true);
                } else {
                    check.add(false);
                }
            }
            if (!check.contains(false)) {
                return true;
            } else {
                check.clear();
            }
        }
        return false;
    }

    public static int point;

    public static HashMap<Integer, HashMap<Integer, Boolean>> getWinningBoard(ArrayList<String> rolls, ArrayList<HashMap<Integer, HashMap<Integer, Boolean>>> k) {
        ArrayList<HashMap<Integer, HashMap<Integer, Boolean>>> tables = new ArrayList<>(k);
        ArrayList<HashMap<Integer, HashMap<Integer, Boolean>>> tablesFake = new ArrayList<>(k);
        boolean winCondition = false;
        ArrayList<Integer> removable = new ArrayList<>();
        HashMap<Integer, HashMap<Integer, Boolean>> currentTable = null;
        HashMap<Integer, HashMap<Integer, Boolean>> winningTable = new HashMap<>();
        while (winCondition == false) {
            boolean restart = false;
            for (String roll : rolls) {
                removable.clear();
                int i = 0;
                int currentRoll = Integer.parseInt(roll);
                for (HashMap<Integer, HashMap<Integer, Boolean>> table : tables) {
                    currentTable = table;
                    for (int cell = 1; cell < 26; cell++) {

                        if (table.get(cell).containsKey(currentRoll)) {
                            table.get(cell).replace(currentRoll, true);
                        }

                    }
                    if (checkWin(table)) {
                        if (tables.size() == 1) {
                            point = currentRoll;
                            return currentTable;
                        }
                        tablesFake.remove(table);
                    }
                tables = new ArrayList<>(tablesFake);

                }


            }
        }
        return winningTable;
    }

    public static int getTheSum(HashMap<Integer, HashMap<Integer, Boolean>> table) {
        int sum = 0;
        for (int cell = 1; cell < 26; cell++) {
            if (table.get(cell).containsValue(false)) {
                ArrayList<Integer> key = new ArrayList<>();
                for (int keys : table.get(cell).keySet()) {
                    key.add(keys);
                }
                sum += key.get(0);
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        ArrayList<String> tables = textManager.getLines("src/resources/day_4_1");
        ArrayList<String> rolls = textManager.getLines("src/resources/day_4_roll");
        ArrayList<HashMap<Integer, HashMap<Integer, Boolean>>> tableMap = createTables(tables);
        HashMap<Integer, HashMap<Integer, Boolean>> winningTable = getWinningBoard(rolls, tableMap);
        System.out.println(winningTable);
        int pointer = (getTheSum(winningTable));
        System.out.println(pointer);
        System.out.println(point);
        System.out.println(pointer * point);

    }
}
