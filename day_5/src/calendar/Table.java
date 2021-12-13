package calendar;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {

    ArrayList<Coordinates> table = new ArrayList<>();

    public Table() {
        for (int row = 0; row < 1000; row++) {
            for (int column = 0; column < 1000; column++) {
                table.add(new Coordinates(column, row));
            }
        }
    }

    public ArrayList<Coordinates> getTable() {
        return table;
    }

    public Coordinates getCoordinates(int row, int column) {
        return table.get(row * 1000 + column);
    }

    public int getTableValue() {
        int bigPoints = 0;
        for (Coordinates coordinates : table) {
            if (coordinates.getValue() >= 2) {
                bigPoints++;
            }
        }
        return bigPoints;
    }

    public void showMap() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + " ");
            for (int j = 0; j < 1000; j++) {
                if (getCoordinates(i,j).getValue() == 0){
                    System.out.print(". ");
                }else{
                    System.out.print(getCoordinates(i, j).getValue() + " ");
                }

            }

        }
    }


    public void updateCoordinates(ArrayList<String> listOfChanges, boolean withDiagonals) {
        Pattern liner = Pattern.compile("[\\d]*[\\d]");
        for (String line : listOfChanges) {
            Matcher m = liner.matcher(line);
            ArrayList<String> movementCoordinates = new ArrayList<>();
            while (m.find()) {
                movementCoordinates.add(m.group());
            }
            int firstRow = Integer.parseInt(movementCoordinates.get(1));
            int firstColumn = Integer.parseInt(movementCoordinates.get(0));
            int secondRow = Integer.parseInt(movementCoordinates.get(3));
            int secondColumn = Integer.parseInt(movementCoordinates.get(2));
            if (firstColumn == secondColumn) {
                if (firstRow < secondRow) {
                    for (int movement = secondRow - firstRow; movement >= 0; movement--) {
                        getCoordinates(firstRow + movement, firstColumn).addValue();
                    }
                } else if (firstRow > secondRow) {
                    for (int movement = secondRow - firstRow; movement <= 0; movement++) {
                        getCoordinates(firstRow + movement, firstColumn).addValue();
                    }
                }
            } else if (firstRow == secondRow) {
                if (firstColumn < secondColumn) {
                    for (int movement = secondColumn - firstColumn; movement >= 0; movement--) {
                        getCoordinates(firstRow, firstColumn + movement).addValue();
                    }
                } else if (firstColumn > secondColumn) {
                    for (int movement = secondColumn - firstColumn; movement <= 0; movement++) {
                        getCoordinates(firstRow, firstColumn + movement).addValue();
                    }

                }
            } else {
                if (withDiagonals) {
                    if (firstRow < secondRow && firstColumn < secondColumn) {
                        for (int movement = secondColumn - firstColumn; movement >= 0; movement--) {
                            getCoordinates(firstRow + movement, firstColumn + movement).addValue();
                        }
                    } else if (firstColumn < secondColumn && firstRow > secondRow) {
                        for (int movement = secondColumn - firstColumn; movement >= 0; movement--) {
                            getCoordinates(firstRow - movement,firstColumn + movement).addValue();
                        }
                    } else if (firstColumn > secondColumn && firstRow < secondRow) {
                        for (int movement = secondRow - firstRow; movement >= 0; movement--) {
                            getCoordinates(firstRow + movement,firstColumn - movement).addValue();
                        }
                    } else {
                        for (int movement = firstRow - secondRow; movement >= 0; movement--) {
                            getCoordinates(firstRow - movement,firstColumn - movement).addValue();
                        }
                    }
                }


            }
        }

    }
}
