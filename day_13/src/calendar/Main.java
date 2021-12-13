package calendar;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static Integer getNumberOfPoints(ArrayList<ArrayList<Integer>> listOfPoints) {
        return listOfPoints.size();
    }

    public static ArrayList<ArrayList<Integer>> folding(ArrayList<ArrayList<Integer>> newList, ArrayList<String> coordinates) {
        ArrayList<ArrayList<Integer>> listOfPoints = new ArrayList<>(newList);
        for (ArrayList<Integer> points : newList) {
            if (coordinates.get(0).equals("x")) {
                if (points.get(0) == Integer.parseInt(coordinates.get(1))) {
                    listOfPoints.remove(points);
                } else if (points.get(0) > Integer.parseInt(coordinates.get(1))) {
                    listOfPoints.remove(new ArrayList<Integer>(Arrays.asList(points.get(0), points.get(1))));
                    if (!listOfPoints.contains(new ArrayList<Integer>(Arrays.asList(points.get(0) - ((points.get(0) - Integer.parseInt(coordinates.get(1))) * 2), points.get(1))))) {
                        listOfPoints.add(new ArrayList<Integer>(Arrays.asList(points.get(0) - ((points.get(0) - Integer.parseInt(coordinates.get(1))) * 2), points.get(1))));
                    }
                }
            } else {
                if (points.get(1) == Integer.parseInt(coordinates.get(1))) {
                    listOfPoints.remove(points);
                } else if (points.get(1) > Integer.parseInt(coordinates.get(1))) {
                    listOfPoints.remove(new ArrayList<Integer>(Arrays.asList(points.get(0), points.get(1))));
                    if (!listOfPoints.contains(new ArrayList<Integer>(Arrays.asList(points.get(0), points.get(1) - ((points.get(1) - Integer.parseInt(coordinates.get(1))) * 2))))) {
                        listOfPoints.add(new ArrayList<Integer>(Arrays.asList(points.get(0), points.get(1) - ((points.get(1) - Integer.parseInt(coordinates.get(1))) * 2))));
                    }
                }
            }
        }
        return listOfPoints;
    }

    public static void writeMap(ArrayList<ArrayList<Integer>> coordinates) {
        for (int row = 0; row <= 50; row++) {
            System.out.println(" ");
            for (int column = 0; column <= 50; column++) {
                if (coordinates.contains(new ArrayList<>(Arrays.asList(column, row)))) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> points = textManager.getNumberLines(textManager.getLinesAsString("src/resources/day_13"));
        ArrayList<ArrayList<String>> coordinates = textManager.getCoordinatesLines(textManager.getLinesAsString("src/resources/day_13_coordinates"));
        for (ArrayList<String> coordinate : coordinates) {
            points = folding(points, coordinate);
            System.out.println(points);
        }
        System.out.println(getNumberOfPoints(points));
        writeMap(points);
    }
}
