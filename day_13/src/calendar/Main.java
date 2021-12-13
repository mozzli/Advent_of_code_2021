package calendar;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static boolean checkIfContains(ArrayList<ArrayList<Integer>> list, int xValue, int yValue) {
        return list.contains(new ArrayList<>(Arrays.asList(xValue, yValue)));
    }

    public static ArrayList<ArrayList<Integer>> folding(ArrayList<ArrayList<Integer>> newList, ArrayList<String> coordinates) {
        ArrayList<ArrayList<Integer>> listOfPoints = new ArrayList<>(newList);
        String foldingPlace = coordinates.get(0);
        int coordinatesPoint = Integer.parseInt(coordinates.get(1));
        int foldedX;
        int foldedY;
        int pointX;
        int pointY;
        for (ArrayList<Integer> points : newList) {
            pointX = points.get(0);
            pointY = points.get(1);
            if (pointX >= coordinatesPoint && foldingPlace.equals("x")) {
                foldedX = pointX - ((pointX - coordinatesPoint) * 2);
                listOfPoints.remove(new ArrayList<>(Arrays.asList(pointX, pointY)));
                if (pointX != coordinatesPoint && !checkIfContains(listOfPoints, foldedX, pointY)) {
                    listOfPoints.add(new ArrayList<>(Arrays.asList(foldedX, pointY)));
                }
            } else if (pointY >= coordinatesPoint && foldingPlace.equals("y")) {
                foldedY = pointY - ((pointY - coordinatesPoint) * 2);
                listOfPoints.remove(new ArrayList<>(Arrays.asList(pointX, pointY)));
                if (pointY != coordinatesPoint && !checkIfContains(listOfPoints, pointX, foldedY)) {
                    listOfPoints.add(new ArrayList<>(Arrays.asList(pointX, foldedY)));
                }
            }
        }
        return listOfPoints;
    }

    public static void writeMap(ArrayList<ArrayList<Integer>> coordinates) {
        int maxRow = 0;
        int maxColumn = 0;
        for (ArrayList<Integer> coordinate:coordinates) {
            if (maxRow<coordinate.get(1)){
                maxRow = coordinate.get(1);
            }
            if (maxColumn<coordinate.get(0)){
                maxColumn = coordinate.get(0);
            }
        }
        for (int row = 0; row <= maxRow; row++) {
            System.out.println(" ");
            for (int column = 0; column <= maxColumn; column++) {
                if (coordinates.contains(new ArrayList<>(Arrays.asList(column, row)))) {
                    System.out.print("0");
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
        }
        System.out.println(points.size());
        writeMap(points);
    }
}
