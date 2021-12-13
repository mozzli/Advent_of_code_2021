package calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static HashMap<Integer, Integer> mapOfCave(ArrayList<ArrayList<Integer>> input) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (ArrayList<Integer> row : input) {
            int j = 1;
            for (Integer column : row) {
                map.put(i * row.size() + j, column);
                j++;
            }
            i++;
        }
        return map;
    }

    public static Integer getLowestPoints(HashMap<Integer, Integer> map, int rowSize, int columnSize) {
        int sum = 0;
        for (int key = 1; key <= map.size(); key++) {
            boolean north = false;
            boolean south = false;
            boolean east = false;
            boolean west = false;
//            check north
            if (key <= rowSize) {
                north = true;
            } else if (map.get(key - rowSize) > map.get(key)) {
                north = true;
            }
//            check south
            if (key >= rowSize * columnSize - rowSize + 1) {
                south = true;
            } else if (map.get(key + rowSize) > map.get(key)) {
                south = true;
            }
//            check east
            if (key % rowSize == 0) {
                east = true;
            } else if (map.get(key + 1) > map.get(key)) {
                east = true;
            }
//            check west
            if (key % rowSize == 1) {
                west = true;
            } else if (map.get(key - 1) > map.get(key)) {
                west = true;
            }
            ArrayList<Boolean> answer = new ArrayList<>(Arrays.asList(north, south, east, west));
            if (!answer.contains(false)) {
                sum += 1 + map.get(key);
            }
        }
        return sum;
    }

    public static int basinCheck(HashMap<Integer, Integer> map, int key, int rowSize, int columnSize, ArrayList<Integer> checkedKeys, boolean vnorth, boolean vsouth, boolean veast, boolean vwest, int tryier) {
        int size = 1;
        checkedKeys.add(key);
        if (map.get(key) < 9) {
            if (vnorth && key > rowSize ) {
                if (!checkedKeys.contains(key - rowSize) && map.get(key - rowSize) !=9 && map.get(key)<map.get(key-rowSize)) {
                    size += basinCheck(map, key - rowSize, rowSize, columnSize, checkedKeys,true,false,true,true, tryier);
                }
            }
            if (vsouth && key < rowSize * columnSize - rowSize + 1 ) {
                if (!checkedKeys.contains(key + rowSize) && map.get(key + rowSize) !=9 && map.get(key)<map.get(key+rowSize)) {
                    System.out.println(map.get(key+rowSize));
                    size += basinCheck(map, key + rowSize, rowSize, columnSize, checkedKeys, false,true,true,true, tryier);
                }
            }
            if (veast && key % rowSize != 0 ) {
                if (!checkedKeys.contains(key + 1) && map.get(key + 1) !=9 && map.get(key)<map.get(key+1) ) {
                    size += basinCheck(map, key + 1, rowSize, columnSize, checkedKeys, true,true,true,false, tryier);
                }
            }
            if (vwest && key % rowSize != 1) {
                if (!checkedKeys.contains(key - 1) && map.get(key - 1) !=9 && map.get(key)<map.get(key-1)) {
                    size += basinCheck(map,key - 1, rowSize, columnSize, checkedKeys, true,true,false,true, tryier);
                }
            }
        }
        return size;
    }

    public static int getBiggestBasinValue(HashMap<Integer, Integer> map, int rowSize, int columnSize) {
        ArrayList<Integer> biggestBasins = new ArrayList<>();
        System.out.println(map);
        for (int i = 1; i <= map.size(); i++) {
            int size = 0;
            size = basinCheck(map,i,rowSize,columnSize,new ArrayList<Integer>(),true,true,true,true,i);
            biggestBasins.add(size);
        }
        System.out.println(biggestBasins);
        Collections.sort(biggestBasins);
        System.out.println(biggestBasins);
        return biggestBasins.get(biggestBasins.size()-1) * biggestBasins.get(biggestBasins.size()-2) * biggestBasins.get(biggestBasins.size()-3);
    }


    public static void main(String[] args) {
        String source = "src/resources/day_9";
        ArrayList<ArrayList<Integer>> input = textManager.getLinesAsInt(source);
        System.out.println("final score: "+getBiggestBasinValue(mapOfCave(input),textManager.getLinesAsInt(source).get(0).size(), textManager.getLinesAsInt(source).size()));
    }
}
