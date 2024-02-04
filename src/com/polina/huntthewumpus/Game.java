package com.polina.huntthewumpus;

import java.util.*;

public class Game {
    public static void main(String[] args) {
        while (true) {
            System.out.println("GAME SETTINGS");
            System.out.println("Choose a connection between the rooms.");
            System.out.println("A simpler connection is the 'corridor'. You can choose the number of rooms.");
            System.out.println("A more complex arrangement of rooms is the 'dodecahedron'. The number of rooms is always 20.");
            Scan getInf = new Scan();
            String roomsLayout = getInf.inputCorOrDod();
            int numberOfRooms = 20;
            if (roomsLayout.equalsIgnoreCase("cor")) {
                numberOfRooms = getInf.inputNumberOfRoomsInCaseCor();
            }

            AllRoomsGenerator allRoomsGenerator = new AllRoomsGenerator(numberOfRooms);
            List<Room> listOfRooms = allRoomsGenerator.generateRooms(roomsLayout);

            int firstRoomNumber = allRoomsGenerator.createFirstRoomNumber();

            Cave cave = new Cave(listOfRooms);

            listOfRooms.get(firstRoomNumber).printInformation(cave);
            Gamer gamer = new Gamer(cave);
            int gamerMove = gamer.makeAMove(listOfRooms.get(firstRoomNumber), numberOfRooms);
            while (gamer.active) {
                listOfRooms.get(gamerMove).printInformation(cave);
                gamerMove = gamer.makeAMove(listOfRooms.get(gamerMove), numberOfRooms);
            }
            System.out.println();
            System.out.println("Repeat? ('yes' / 'no')");
            String repeat = getInf.nextLine();
            if (repeat.equals("no")) {
                break;
            }
        }
    }
}