package calendar;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void createNeighbours(HashMap<String,Cave> map, ArrayList<ArrayList<String>> coordinates){
        for (ArrayList<String> coordinate: coordinates){
            map.get(coordinate.get(0)).addNeighbour(coordinate.get(1));
            map.get(coordinate.get(1)).addNeighbour(coordinate.get(0));
        }
    }

    public static HashMap<String ,Cave> createMap(ArrayList<ArrayList<String>> listOfCoordinates){
        HashMap<String,Cave> map = new HashMap<>();
        ArrayList<String> caves = new ArrayList<>();
        for (ArrayList<String> coordinate: listOfCoordinates){
            for (String cave: coordinate){
                if (!caves.contains(cave))
                    caves.add(cave);
            }
        }
        for (String cave: caves) {
            boolean big = false;
            if (cave.matches("^[A-Z]*$")){
                big = true;
            }
            map.put(cave, new Cave(cave,big));
        }
        createNeighbours(map,listOfCoordinates);
        return map;
    }

    public static void findPathsWithDoubleCaves(HashMap<String,Cave> map,
                                 ArrayList<ArrayList<String>> listOfPaths,
                                 ArrayList<String> pathHistory,
                                 String currentCave,
                                 boolean wasInCave){
        ArrayList<String> currentPath = new ArrayList<>(pathHistory);
        if (map.get(currentCave).name.equals("end")){
            currentPath.add(map.get(currentCave).name);
            listOfPaths.add(currentPath);
        }else if (map.get(currentCave).checkIfDeadEndWithDoubleCaves(currentPath, wasInCave)){
            ;
        }
        else{
            boolean caveCheck = wasInCave;
            if (!wasInCave){
                caveCheck = map.get(currentCave).wasInCave(currentPath);
            }
            currentPath.add(map.get(currentCave).name);
            for (String neighbour: map.get(currentCave).getNeighbours()) {
                if(!map.get(neighbour).name.equals("start")){
                findPathsWithDoubleCaves(map,listOfPaths,currentPath,neighbour, caveCheck);}


            }
        }
    }

    public static void findPaths(HashMap<String,Cave> map,
                                 ArrayList<ArrayList<String>> listOfPaths,
                                 ArrayList<String> pathHistory,
                                 String currentCave){
        ArrayList<String> currentPath = new ArrayList<>(pathHistory);
        if (map.get(currentCave).name.equals("end")){
            currentPath.add(map.get(currentCave).name);
            listOfPaths.add(currentPath);
        }else if (map.get(currentCave).checkIfDeadEnd(currentPath)){
            ;
        }
        else{
            currentPath.add(map.get(currentCave).name);
            for (String neighbour: map.get(currentCave).getNeighbours()) {
                    findPaths(map,listOfPaths,currentPath,neighbour);
            }
        }
    }


    public static ArrayList<ArrayList<String>> getListOfPaths(HashMap<String,Cave> map, boolean doubleCave){
        ArrayList<ArrayList<String>> listOfPaths = new ArrayList<>();
        if (doubleCave){
        findPathsWithDoubleCaves(map,listOfPaths,new ArrayList<String>(),"start",false);}
        else {
            findPaths(map,listOfPaths,new ArrayList<String>(),"start");
        }
        return listOfPaths;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<String>>listOfCoordinates = textManager.getTextLines(textManager.getLinesAsString("src/resources/day_12"));
        HashMap<String,Cave> map = createMap(listOfCoordinates);
        System.out.println(getListOfPaths(map,false).size());
    }
}
